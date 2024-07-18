package com.example.usersservice.entities;

import javax.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Avocat extends User {
    private String spécialité;
    private boolean consultation_tel;
    private boolean consultation_ecrit;

}
