package com.example.consultationservice.entities;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Creneaux {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String heure_debut;
    private String heure_fin;

    @OneToMany(mappedBy = "créneau", cascade = CascadeType.ALL)
    private List<Consultation> consultations = new ArrayList<>();

    public Creneaux(String heure_debut, String heure_fin) {
        this.heure_debut = heure_debut;
        this.heure_fin = heure_fin;
    }

    @Override
    public String toString() {
        return "Creneaux{" +
                "id=" + id +
                ", heure_debut='" + heure_debut + '\'' +
                ", heure_fin='" + heure_fin + '\'' +
//                ", consultations=" + consultations +
                '}';
    }

    public Creneaux() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getHeure_debut() {
        return heure_debut;
    }

    public void setHeure_debut(String heure_debut) {
        this.heure_debut = heure_debut;
    }

    public String getHeure_fin() {
        return heure_fin;
    }

    public void setHeure_fin(String heure_fin) {
        this.heure_fin = heure_fin;
    }

    public List<Consultation> getConsultations() {
        return consultations;
    }

    public void setConsultations(List<Consultation> consultations) {
        this.consultations = consultations;
    }
}
