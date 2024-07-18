package com.example.userservice.services;


import com.example.userservice.dtos.SearchAvocatDto;
import com.example.userservice.dtos.UserInputDto;
import com.example.userservice.dtos.UserOutPutDto;
import com.example.userservice.entities.Avocat;
import com.example.userservice.entities.User;
import com.example.userservice.enums.role;
import com.example.userservice.exceptions.MissingFieldException;
import com.example.userservice.exceptions.UserCinExistException;
import com.example.userservice.exceptions.UserNotFoundException;
import com.example.userservice.mappers.UserMapper;
import com.example.userservice.repositories.AvocatRepository;
import com.example.userservice.utils.KeycloakUtils;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static com.example.userservice.utils.UserUtils.checkAvocatInputDtoFields;


@Service
@Transactional
@AllArgsConstructor
//@NoArgsConstructor
public class AvocatServiceImpl implements userService {
    AvocatRepository avocatRepository;
    UserMapper userMapper;
    KeycloakUtils keycloakUtils;

    @Override
    public List<UserOutPutDto> allUsers() {
        return avocatRepository.findAll()
                .stream()
                .map(userMapper::UserToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(String id) throws UserNotFoundException {
        Avocat avocat = avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        return userMapper.AvocatToUserOutPutDto(avocat);
    }

    public Avocat singleUserByEmail(String email) throws UserNotFoundException {
        Avocat avocat = avocatRepository.findByEmail(email);
        return avocat;
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        Avocat avocat = this.userMapper.UserInputDtoToAvocat(userInputDto);
        userInputDto.setRole(role.avocat_app.toString());
        avocat.setId(keycloakUtils.createKeycloakUserWithRole(userInputDto));
        System.out.println("avocat;  ");
        System.out.println(avocat);
        System.out.println(avocat.getId());

        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(avocatRepository.save(avocat));
        System.out.println("user mapper" + avocat);
        return userOutPutDto;
//        return null;
    }

    @Override
    public void deleteUser(String id) throws UserNotFoundException {
        Avocat avocat = avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id));
        keycloakUtils.deleteUser(id);

        avocatRepository.delete(avocat);

    }

    @Override
    public UserOutPutDto updateUser(String id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        avocatRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        Avocat avocat = userMapper.UserInputDtoToAvocat(userInputDto);
        avocat.setId(id);
        userInputDto.setRole(role.avocat_app.toString());
        keycloakUtils.updateKeycloakUser(id, userInputDto);


        return userMapper.AvocatToUserOutPutDto(avocatRepository.save(avocat));
    }


    public List<UserOutPutDto> avocatsByNomVilleSpecialite(SearchAvocatDto searchAvocatDto) {

        List<Avocat> listAvocats = avocatRepository.findAll();
        System.out.println(searchAvocatDto);
//        System.out.println(searchAvocatDto);

        if (searchAvocatDto.getNom() != null && !searchAvocatDto.getNom().isEmpty()) {
            System.out.println("from nom");
            listAvocats = (List<Avocat>) listAvocats
                    .stream()
                    .filter(avocat -> avocat.getNom().equals(searchAvocatDto.getNom()))
                    .collect(Collectors.toList());

        }
        if (searchAvocatDto.getPrénom() != null && !searchAvocatDto.getPrénom().isEmpty()) {
            System.out.println("from prenom");

            listAvocats = (List<Avocat>) listAvocats
                    .stream()
                    .filter(avocat -> avocat.getPrénom().equals(searchAvocatDto.getPrénom()))
                    .collect(Collectors.toList());

        }
        if (searchAvocatDto.getVille() != null && !searchAvocatDto.getVille().isEmpty()) {
            System.out.println("from ville");
            listAvocats = (List<Avocat>) listAvocats
                    .stream()
                    .filter(avocat -> avocat.getVille().equals(searchAvocatDto.getVille()))
                    .collect(Collectors.toList());

        }
        if (searchAvocatDto.getSpécialité() != null && !searchAvocatDto.getSpécialité().isEmpty()) {
            System.out.println("from spec");
            listAvocats = (List<Avocat>) listAvocats
                    .stream()
                    .filter(avocat -> avocat.getSpécialité().equals(searchAvocatDto.getSpécialité()))
                    .collect(Collectors.toList());

        }


        return listAvocats
                .stream()
                .map(userMapper::UserToUserOutPutDto)
                .collect(Collectors.toList());
    }

    public void validerAvocat(String id) {
        avocatRepository.validerAvocat(avocatRepository.findById(id).get().getId());
    }


}
