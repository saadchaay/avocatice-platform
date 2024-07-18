package com.example.usersservice.mappers;//package com.example.userservice.mappers;
//

import com.example.usersservice.entities.Agent;
import com.example.usersservice.entities.Avocat;
import com.example.usersservice.entities.Client;
import com.example.usersservice.entities.User;
import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    User UserInputDtoToUser(UserInputDto userInputDto);

    UserOutPutDto UserToUserOutPutDto(User user);


    Avocat UserInputDtoToAvocat(UserInputDto userInputDto);

    UserOutPutDto AvocatToUserOutPutDto(Avocat avocat);

    Agent UserInputDtoToAgent(UserInputDto userInputDto);

    UserOutPutDto AgentToUserOutPutDto(Agent agent);

    Client UserInputDtoToClient(UserInputDto userInputDto);

    UserOutPutDto clientToUserOutPutDto(Client client);


}

