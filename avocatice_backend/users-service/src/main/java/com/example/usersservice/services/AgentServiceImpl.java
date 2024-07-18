package com.example.usersservice.services;


import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.entities.Agent;
import com.example.usersservice.entities.Client;
import com.example.usersservice.exceptions.MissingFieldException;
import com.example.usersservice.exceptions.UserCinExistException;
import com.example.usersservice.exceptions.UserNotFoundException;
import com.example.usersservice.mappers.UserMapper;
import com.example.usersservice.repositories.AgentRepository;
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
public class AgentServiceImpl implements userService {
    AgentRepository agentRepository;
    UserMapper userMapper;

    @Override
    public List<UserOutPutDto> allUsers() {
        return agentRepository.findAll()
                .stream()
                .map(userMapper::AgentToUserOutPutDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserOutPutDto singleUser(Long id) throws UserNotFoundException {
        Agent agent = agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        return userMapper.AgentToUserOutPutDto(agent);
    }

    @Override
    public UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException {

        if (checkAvocatInputDtoFields(userInputDto)) throw new MissingFieldException("Missing Field");
        System.out.println("user Input" + userInputDto);
        Agent agent = this.userMapper.UserInputDtoToAgent(userInputDto);
        UserOutPutDto userOutPutDto = userMapper.UserToUserOutPutDto(agentRepository.save(agent));
        System.out.println("user mapper" + agent);
        KeycloakUtils.createKeycloakUserWithRole(userInputDto);
        return userOutPutDto;
//        return null;
    }

    @Override
    public void deleteUser(Long id) throws UserNotFoundException {
        Agent client = agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        agentRepository.delete(client);
    }

    @Override
    public UserOutPutDto updateUser(Long id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException {
        if (checkAvocatInputDtoFields(userInputDto))
            throw new MissingFieldException("Missing Field");
        agentRepository.findById(id).orElseThrow(() -> new UserNotFoundException(id.toString()));
        Agent agent = userMapper.UserInputDtoToAgent(userInputDto);
        agent.setId(id);
        return userMapper.AgentToUserOutPutDto(agentRepository.save(agent));
    }
}
