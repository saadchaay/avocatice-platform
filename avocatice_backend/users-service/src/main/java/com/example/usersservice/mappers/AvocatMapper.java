package com.example.usersservice.mappers;//package com.example.userservice.mappers;
//

import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.entities.Agent;
import com.example.usersservice.entities.Avocat;
import com.example.usersservice.entities.Client;
import com.example.usersservice.entities.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", uses = {UserMapper.class})
public interface AvocatMapper {
    User UserInputDtoToUser(UserInputDto userInputDto);

    UserOutPutDto UserToUserOutPutDto(User user);


    Avocat UserInputDtoToAvocat(UserInputDto userInputDto);

    UserOutPutDto AvocatToUserOutPutDto(Avocat avocat);

    Agent UserInputDtoToAgent(UserInputDto userInputDto);

    UserOutPutDto AgentToUserOutPutDto(Agent agent);

    Client clientInputDtoToAvocat(UserInputDto userInputDto);

    UserOutPutDto clientToUserOutPutDto(Client client);


}

