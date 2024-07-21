package com.example.consultationservice.web;


import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.models.dto.CreneauxDto;
import com.example.consultationservice.services.CreneauService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("api")
public class CreneauRestController {
    CreneauService creneauService;

    public CreneauRestController(CreneauService creneauService) {
        this.creneauService = creneauService;
    }

    @GetMapping("/crenaux/{date}")
    public  List<CreneauxDto> getCreneaux(@PathVariable String date) throws ParseException {
        System.out.println("from get cren");
        return creneauService.allCreneaux(date);
    }

    @GetMapping("/a")
    public void z(){
        System.out.println("fasr");
    }

//    @PostMapping("/client")
//    public UserOutPutDto addClient(@RequestBody UserInputDto userInputDto) throws UserCinExistException, MissingFieldException {
//        return clientService.createUser(userInputDto);
//    }
//
//
//    @GetMapping("/client/{id}")
//    public UserOutPutDto getClient(@PathVariable String id) throws UserNotFoundException {
//        return clientService.singleUser(id);
//    }
//
//    @DeleteMapping("/client/{id}")
//    public void deleteClient(@PathVariable String id) throws UserNotFoundException {
//        clientService.deleteUser(id);
//    }
//
//
//    @PutMapping("/client/{id}")
//    public UserOutPutDto updateClient(@PathVariable String id, @RequestBody UserInputDto userInputDto) throws UserNotFoundException, MissingFieldException {
//        return clientService.updateUser(id, userInputDto);
//    }
}
