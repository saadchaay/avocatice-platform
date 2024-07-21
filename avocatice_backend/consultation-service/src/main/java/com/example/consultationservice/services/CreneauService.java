package com.example.consultationservice.services;


import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.models.dto.CreneauxDto;
import com.example.consultationservice.repositories.CreneuaxRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CreneauService {


    CreneuaxRepository creneuaxRepository;
    ConsultationService consultationService;

//    public CreneauService(CreneuaxRepository creneuaxRepository, ConsultationService consultationService) {
//        this.creneuaxRepository = creneuaxRepository;
//        this.consultationService = consultationService;
//    }

    public Creneaux createCreneaux(Creneaux creneaux) {
        creneuaxRepository.save(creneaux);
        System.out.println(creneaux);
        return creneaux;
    }

    public Creneaux getCreneux(Long id) {
        return creneuaxRepository.findById(id).get();
    }

    public List<Creneaux> allCreneaux() {
        return creneuaxRepository.findAll();
    }


    public List<CreneauxDto> allCreneaux(String date) throws ParseException {
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date1 = dateFormat.parse(date);
        List<Consultation> consultationsCrenaux = null;
        consultationsCrenaux = consultationService.allConsultation();

        List<Creneaux> crenuax = creneuaxRepository.findAll();
        if (consultationsCrenaux.size() == 0 || consultationsCrenaux == null) {
            return CreneauxDto.toDtoList(crenuax);

        }
        for (Consultation consultation : consultationsCrenaux) {
            Creneaux current;
            System.out.println(consultation.getDate_créneau());
            if (consultation.getDate_créneau().compareTo(date1) == 0) {
                current = consultation.getCréneau();
            } else {
                current = null;
            }
            if (current != null) {

                crenuax.removeIf(creneaux -> creneaux.getId() == current.getId());

            }

        }

        return CreneauxDto.toDtoList(crenuax);
    }
}
