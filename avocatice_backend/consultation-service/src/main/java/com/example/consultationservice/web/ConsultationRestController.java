package com.example.consultationservice.web;


import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.services.ConsultationService;
import com.example.consultationservice.services.CreneauService;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("api")
public class ConsultationRestController {
    ConsultationService consultationService;

    public ConsultationRestController(ConsultationService consultationService) {
        this.consultationService = consultationService;
    }

//    @GetMapping("/crenaux/{date}")
//    public  List<Creneaux> getCreneaux(@PathVariable String date) throws ParseException {
//        System.out.println("from get cren");
//        return creneauService.allCreneaux(date);
//    }

//    @GetMapping("/a")
//    public void z(){
//        System.out.println("fasr");
//    }

    @PostMapping("/consultation")
    public Consultation addConsultation(@RequestBody Consultation consultation) throws ParseException {
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
//        Date date = dateFormat.parse(String.valueOf(consultation.getDate_créneau()));
//       consultation.setDate_créneau(date);
        System.out.println(consultation);
        return consultationService.createConsultation(consultation);
    }

    @GetMapping("/consultation/client/{id}")
    public List<Consultation> getConsultationByClient(@PathVariable String id) {
        return consultationService.allConsultationByClient(id);
    }

    @GetMapping("/consultation/avocat/{id}")
    public List<Consultation> getConsultationByAvocat(@PathVariable String id) {
        return consultationService.allConsultationByAvocatId(id);
    }


    @GetMapping("/consultation/valider/{id}")
    public void validerConsultation(@PathVariable long id) {
        consultationService.validerConsultation(id);
    }
}
