package com.example.consultationservice.models;

import lombok.Data;

@Data
public class Client {
    private String clientId;
    private String email;
    private String nom;
    private String prénom;
    private String cin;
    private String gender;
    private String ville;
    private String role;
}
