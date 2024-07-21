package com.example.consultationservice.services;


import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.models.dto.ConsultationDto;
import com.example.consultationservice.repositories.ConsultationRepository;
import com.example.consultationservice.repositories.CreneuaxRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ConsultationService {

    ConsultationRepository consultationRepository;
    CreneuaxRepository creneuaxRepository;
    UserRestClient userRestClient;

    public ConsultationService(ConsultationRepository consultationRepository, CreneuaxRepository creneuaxRepository, UserRestClient userRestClient) {
        this.consultationRepository = consultationRepository;
        this.creneuaxRepository = creneuaxRepository;
        this.userRestClient = userRestClient;
    }

    public Consultation createConsultation(Consultation consultation) {
        consultation.setAvocatId(consultation.getAvocatId());
        consultation.setClientId(consultation.getClientId());
        if (consultation.getCréneau() == null) {
            Creneaux creneaux = creneuaxRepository.findById(consultation.getId_creneaux()).orElse(null);
            consultation.setCréneau(creneaux);
            consultation.setAvocatId(userRestClient.getAvocatByEmail(consultation.getAvocatId()).getId());
        }
        System.out.println(consultation);
        consultationRepository.save(consultation);
        return consultation;
    }

    public List<Consultation> allConsultation() {
        return consultationRepository.findAll();
    }


    public List<ConsultationDto> allConsultationByAvocatId(String id) {


        List<Consultation> collect = consultationRepository.findAll()
                .stream()
                .filter(consultation -> id.equals(consultation.getAvocatId()))
                .collect(Collectors.toList());
        System.out.println(collect);
        for (Consultation c : collect) {
            c.setAvocat(userRestClient.getAvocat(c.getAvocatId()));

            c.setClient(userRestClient.getClient(c.getClientId()));
        }
        System.out.println(collect);

        return ConsultationDto.toDtoList(collect);
    }

    public List<ConsultationDto> allConsultationByClient(String id) {
        List<Consultation> collect = consultationRepository.findAll()
                .stream()
                .filter(consultation -> id.equals(consultation.getClientId()))
                .collect(Collectors.toList());
        for (Consultation c : collect) {
            c.setAvocat(userRestClient.getAvocat(c.getAvocatId()));
            c.setClient(userRestClient.getClient(c.getClientId()));
        }
        return ConsultationDto.toDtoList(collect);
//        return collect;

    }

     public void validerConsultation(Long id) {
        consultationRepository.validerConsultation(id);
    }


}
