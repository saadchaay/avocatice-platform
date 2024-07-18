package com.example.consultationservice.models;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Avocat {
    private String id;
    private String email;
    private String nom;
    private String prénom;
    private String cin;
    private String gender;
    private String ville;
    private String spécialité;
    private String role;
}
