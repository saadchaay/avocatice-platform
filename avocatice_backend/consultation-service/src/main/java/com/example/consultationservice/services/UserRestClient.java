package com.example.consultationservice.services;

import com.example.consultationservice.models.Avocat;
import com.example.consultationservice.models.Client;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Service
@FeignClient(name = "user-service")
public interface UserRestClient {

    @GetMapping(path = "api/avocat/{id}")
    Avocat getAvocat(@PathVariable String id);

    @GetMapping(path = "api/avocat/email/{email}")
    Avocat getAvocatByEmail(@PathVariable String email);

    @GetMapping(path = "api/client/{id}")
    Client getClient(@PathVariable String id);

}
