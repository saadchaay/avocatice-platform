package com.example.usersservice.web;

import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.dtos.UserOutPutDto;
import com.example.usersservice.exceptions.MissingFieldException;
import com.example.usersservice.exceptions.UserCinExistException;
import com.example.usersservice.exceptions.UserNotFoundException;
import com.example.usersservice.services.AvocatServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping("api")
public class AvocatRestController {
    AvocatServiceImpl avocatService;


    @GetMapping("/avocat-list")
    public List<UserOutPutDto> getAvocatList() {
        return avocatService.allUsers();
    }

    @PostMapping("/avocat")
    public UserOutPutDto addAvocat(@RequestBody UserInputDto userInputDto) throws UserCinExistException, MissingFieldException {
        return avocatService.createUser(userInputDto);
    }


    @GetMapping("/avocat/{id}")
    public UserOutPutDto getAvocat(@PathVariable Long id) throws UserNotFoundException {
        return avocatService.singleUser(id);
    }

    @DeleteMapping("/avocat/{id}")
    public void deleteAvocat(@PathVariable Long id) throws UserNotFoundException {
        avocatService.deleteUser(id);
    }


    @PutMapping("/avocat/{id}")
    public UserOutPutDto updateProfessor(@PathVariable Long id, @RequestBody UserInputDto userInputDto) throws UserNotFoundException, MissingFieldException {
        return avocatService.updateUser(id, userInputDto);
    }
}
