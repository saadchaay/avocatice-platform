package com.example.usersservice;

import com.example.usersservice.dtos.UserInputDto;
import com.example.usersservice.enums.Gender;
import com.example.usersservice.services.AvocatServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class UsersServiceApplication {
    @Value("${serverUrl}")
    String test;


    public static void main(String[] args) {

        SpringApplication.run(UsersServiceApplication.class, args);
    }

    @Bean
    CommandLineRunner start(AvocatServiceImpl avocatService) {
        System.out.println(this.test);

        return args -> {
            double i = 421;
            System.out.println("tesss" + i);
            UserInputDto avocat = new UserInputDto();
            avocat.setEmail("avocat" + i + "@gmail.com");
            avocat.setCin("WA123" + i);
            avocat.setGender(Math.random() * 10 > 5 ? Gender.MALE.toString() : Gender.FEMALE.toString());
            avocat.setNom("avocat " + i);
            avocat.setRole("AVOCAT");
            avocat.setPrénom("avocat Prenom :" + i);
            avocat.setPassword("123");
            avocat.setVille("casa" + i);
            avocat.setSpécialité("avocat SP " + i);
            avocat.setImage_url("azertyuiop");
            avocatService.createUser(avocat);

        };
    }
}
