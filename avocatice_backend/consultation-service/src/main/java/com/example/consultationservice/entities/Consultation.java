package com.example.consultationservice.entities;

import com.example.consultationservice.models.Avocat;
import com.example.consultationservice.models.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Consultation {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    @Column(name = "id", nullable = false)
    private Long id;

    private String sjt_consultation;

    private String type;
    private String reponse = null;

    @DateTimeFormat(pattern = "dd-MM-yyyy")
    @JsonFormat(pattern = "dd-MM-yyyy")
    private Date date_créneau;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_creneau")
    private Creneaux créneau;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String clientId;
    @Transient
    private Client client;

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

    public Long getId_creneaux() {
        return id_creneaux;
    }

    public void setId_creneaux(Long id_creneaux) {
        this.id_creneaux = id_creneaux;
    }

    private Long id_creneaux;
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String AvocatId;
    @Transient
    private Avocat avocat;

    @Override
    public String toString() {
        return "Consultation{" +
                "sjt_consultation='" + sjt_consultation + '\'' +
                ", type='" + type + '\'' +
                ", date_créneau=" + date_créneau +
                ", créneau=" + créneau +
                ", clientId='" + clientId + '\'' +
                ", client=" + client +
                ", AvocatId='" + AvocatId + '\'' +
                ", avocat=" + avocat +
                '}';
    }

    public String getSjt_consultation() {
        return sjt_consultation;
    }

    public void setSjt_consultation(String sjt_consultation) {
        this.sjt_consultation = sjt_consultation;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public Date getDate_créneau() {
        return date_créneau;
    }

    public void setDate_créneau(Date date_créneau) {
        this.date_créneau = date_créneau;
    }

    public Creneaux getCréneau() {
        return créneau;
    }

    public void setCréneau(Creneaux créneau) {
        this.créneau = créneau;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getAvocatId() {
        return AvocatId;
    }

    public void setAvocatId(String avocatId) {
        AvocatId = avocatId;
    }

    public Avocat getAvocat() {
        return avocat;
    }

    public void setAvocat(Avocat avocat) {
        this.avocat = avocat;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
