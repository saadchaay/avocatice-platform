package com.example.consultationservice.services;


import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
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


    public List<Consultation> allConsultationByAvocatId(String id) {


        List<Consultation> collect = consultationRepository.findAll()
                .stream()
                .filter(consultation -> consultation.getAvocatId().equals(id))
//                .map(consultation -> consultation.setAvocat(
//                        userRestClient.getAvocat(
//                                consultation.getAvocatId())))

                .collect(Collectors.toList());
        System.out.println(collect);
        for (Consultation c : collect) {
            c.setAvocat(userRestClient.getAvocat(c.getAvocatId()));

            c.setClient(userRestClient.getClient(c.getClientId()));
        }
        System.out.println(collect);

        return collect;
    }

    public List<Consultation> allConsultationByClient(String id) {
        List<Consultation> collect = consultationRepository.findAll()
                .stream()
                .filter(consultation -> consultation.getClientId().equals(id))
                .collect(Collectors.toList());


        for (Consultation c : collect) {
            c.setAvocat(userRestClient.getAvocat(c.getAvocatId()));
            c.setClient(userRestClient.getClient(c.getClientId()));
        }
        return collect;

    }

     public void validerConsultation(Long id) {
        consultationRepository.validerConsultation(id);
    }


}
