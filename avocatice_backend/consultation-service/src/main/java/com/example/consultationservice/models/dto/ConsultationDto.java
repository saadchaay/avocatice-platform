package com.example.consultationservice.models.dto;

import com.example.consultationservice.entities.Consultation;
import com.example.consultationservice.entities.Creneaux;
import com.example.consultationservice.models.Client;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ConsultationDto {
  private Long id;
  private String sjtConsultation;
  private String type;
  private String reponse;

  @JsonFormat(pattern = "dd-MM-yyyy")
  private Date dateCreneau;
//
  private Long creneauId;
  private String clientId;
  private String avocatId;

  private Client client;
  private CreneauxDto créneau;


  public static ConsultationDto toDto(Consultation consultation) {
    ConsultationDto consultationDTO = new ConsultationDto();
    consultationDTO.setId(consultation.getId());
    consultationDTO.setSjtConsultation(consultation.getSjt_consultation());
    consultationDTO.setType(consultation.getType());
    consultationDTO.setReponse(consultation.getReponse());
    consultationDTO.setDateCreneau(consultation.getDate_créneau());
    consultationDTO.setCreneauId(consultation.getCréneau() != null ? consultation.getCréneau().getId() : null);
    consultationDTO.setClientId(consultation.getClientId());
    consultationDTO.setAvocatId(consultation.getAvocatId());
    consultationDTO.setClient(consultation.getClient());
//    consultationDTO.setCréneau(CreneauxDto.toDto(consultation.getCréneau()));

    return consultationDTO;
  }

  public static List<ConsultationDto> toDtoList(List<Consultation> consultation) {
    return consultation.stream().map(ConsultationDto::toDto).collect(Collectors.toList());
  }

  public static Consultation toEntity(ConsultationDto consultationDTO) {
    Consultation consultation = new Consultation();
    consultation.setId(consultationDTO.getId());
    consultation.setSjt_consultation(consultationDTO.getSjtConsultation());
    consultation.setType(consultationDTO.getType());
    consultation.setReponse(consultationDTO.getReponse());
    consultation.setDate_créneau(consultationDTO.getDateCreneau());

    if (consultationDTO.getCreneauId() != null) {
      Creneaux creneaux = new Creneaux();
      creneaux.setId(consultationDTO.getCreneauId());
      consultation.setCréneau(creneaux);
    }
//
    consultation.setClientId(consultationDTO.getClientId());
    consultation.setAvocatId(consultationDTO.getAvocatId());

    return consultation;
  }


}

