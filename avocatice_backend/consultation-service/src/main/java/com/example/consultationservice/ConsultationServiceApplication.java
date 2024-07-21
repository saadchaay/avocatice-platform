package com.example.consultationservice;

import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.repositories.ConsultationRepository;
import com.example.consultationservice.services.ConsultationService;
import com.example.consultationservice.services.CreneauService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
@EnableFeignClients
public class ConsultationServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(ConsultationServiceApplication.class, args);
    }


//    @Bean
    CommandLineRunner start(CreneauService creneauService, ConsultationService consultationService) {
        return args -> {
            for (int i = 8; i < 18; i++) {
                Creneaux creneaux = new Creneaux();
                creneaux.setHeure_debut(String.valueOf(i));
                creneaux.setHeure_fin(String.valueOf(i + 1));
                creneauService.createCreneaux(creneaux);

            }
            for (int i = 16; i < 22; i++) {
                Consultation consultation = new Consultation();
                String dateString = i + "-04-2023";
                SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
                Date date = dateFormat.parse(dateString);

                consultation.setSjt_consultation("azerty");
                consultation.setType("Ecrite");
//                consultation.setAvocatId("21221");
//                consultation.setClientId("21221");
                consultation.setDate_créneau(date);
                consultation.setCréneau(creneauService.getCreneux(2L));

                consultationService.createConsultation(consultation);


            }


        };
    }

}
