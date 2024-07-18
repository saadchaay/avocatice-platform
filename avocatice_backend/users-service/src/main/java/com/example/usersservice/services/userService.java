package com.example.usersservice.services;

import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.exceptions.MissingFieldException;
import com.example.usersservice.exceptions.UserCinExistException;
import com.example.usersservice.exceptions.UserNotFoundException;

import java.util.List;

public interface userService {
    List<UserOutPutDto> allUsers(); //Get All

    UserOutPutDto singleUser(Long id) throws UserNotFoundException; //Get Elem By Id

    UserOutPutDto createUser(UserInputDto userInputDto) throws MissingFieldException, UserCinExistException; //Create New Elem

    void deleteUser(Long id) throws UserNotFoundException; //Delete Elem By Id

    UserOutPutDto updateUser(Long id, UserInputDto userInputDto) throws MissingFieldException, UserNotFoundException; //Update Elem
    //Search Elems By Keyword
}
