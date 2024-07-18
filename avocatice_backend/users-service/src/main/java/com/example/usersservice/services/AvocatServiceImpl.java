package com.example.usersservice.services;


import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.entities.Avocat;
import com.example.usersservice.exceptions.MissingFieldException;
import com.example.usersservice.exceptions.UserCinExistException;
import com.example.usersservice.exceptions.UserNotFoundException;
import com.example.usersservice.mappers.UserMapper;
import com.example.usersservice.repositories.AvocatRepository;
//import com.example.usersservice.utils.KeycloakUtils;
import com.example.usersservice.utils.KeycloakUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.usersservice.utils.UserUtils.checkAvocatInputDtoFields;


@Service
@Transactional
@AllArgsConstructor
//@NoArgsConstructor
public class AvocatServiceImpl implements userService {
    AvocatRepository avocatRepository;
    UserMapper userMapper;

    @Override
    public List<UserOutPutDto> allUsers() {
        return avocatRepository.findAll()
                .stream()
                .map(userMapper::UserToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(Long id) throws UserNotFoundException {
        Avocat avocat = avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.AvocatToUserOutPutDto(avocat);
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        System.out.println("user Input" + userInputDto);
        Avocat avocat = this.userMapper.UserInputDtoToAvocat(userInputDto);
        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(avocatRepository.save(avocat));
        System.out.println("user mapper" + avocat);
        KeycloakUtils.createKeycloakUserWithRole(userInputDto);
        return userOutPutDto;
//        return null;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        Avocat avocat = avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        avocatRepository.delete(avocat);
    }

    @Override
    public UserOutPutDto updateUser(Long id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        Avocat avocat = userMapper.UserInputDtoToAvocat(userInputDto);
        avocat.setId(id);
        return userMapper.AvocatToUserOutPutDto(avocatRepository.save(avocat));
    }
}
