package com.example.userservice.dtos;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class SearchAvocatDto {

    private String nom;

    @Override
    public String toString() {
        return "SearchAvocatDto{" +
                "nom='" + nom + '\'' +
                ", prénom='" + prénom + '\'' +
                ", ville='" + ville + '\'' +
                ", spécialité='" + spécialité + '\'' +
                '}';
    }

    private String prénom;

    private String ville;
    private String spécialité;




    public SearchAvocatDto() {
    }

    //login


}
