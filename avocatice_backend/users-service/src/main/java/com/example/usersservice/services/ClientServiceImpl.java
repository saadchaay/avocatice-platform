package com.example.usersservice.services;


import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.entities.Avocat;
import com.example.usersservice.entities.Client;
import com.example.usersservice.exceptions.MissingFieldException;
import com.example.usersservice.exceptions.UserCinExistException;
import com.example.usersservice.exceptions.UserNotFoundException;
import com.example.usersservice.mappers.UserMapper;
import com.example.usersservice.repositories.AvocatRepository;
import com.example.usersservice.repositories.ClientRepository;
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
public class ClientServiceImpl implements userService {
    ClientRepository clientRepository;
    UserMapper userMapper;

    @Override
    public List<UserOutPutDto> allUsers() {
        return clientRepository.findAll()
                .stream()
                .map(userMapper::clientToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(Long id) throws UserNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.clientToUserOutPutDto(client);
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        System.out.println("user Input" + userInputDto);
        Client client = this.userMapper.UserInputDtoToClient(userInputDto);
        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(clientRepository.save(client));
        System.out.println("user mapper" + client);
        KeycloakUtils.createKeycloakUserWithRole(userInputDto);
        return userOutPutDto;
//        return null;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        Client client = clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        clientRepository.delete(client);
    }

    @Override
    public UserOutPutDto updateUser(Long id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        clientRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        Client client = userMapper.UserInputDtoToClient(userInputDto);
        client.setId(id);
        return userMapper.clientToUserOutPutDto(clientRepository.save(client));
    }
}
