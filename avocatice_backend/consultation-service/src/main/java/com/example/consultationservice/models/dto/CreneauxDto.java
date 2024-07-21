package com.example.consultationservice.models.dto;

import com.example.consultationservice.entities.Creneaux;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.stream.Collectors;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CreneauxDto {
  private Long id;
  private String heureDebut;
  private String heureFin;
  private List<ConsultationDto> consultations;

  public static CreneauxDto toDto(Creneaux creneaux) {
    CreneauxDto creneauxDTO = new CreneauxDto();
    creneauxDTO.setId(creneaux.getId());
    creneauxDTO.setHeureDebut(creneaux.getHeure_debut());
    creneauxDTO.setHeureFin(creneaux.getHeure_fin());
//    creneauxDTO.setConsultations(
//      creneaux.getConsultations().stream()
//        .map(ConsultationDto::toDto)
//        .collect(Collectors.toList())
//    );

    return creneauxDTO;
  }

  public static Creneaux toEntity(CreneauxDto creneauxDTO) {
    Creneaux creneaux = new Creneaux();
    creneaux.setId(creneauxDTO.getId());
    creneaux.setHeure_debut(creneauxDTO.getHeureDebut());
    creneaux.setHeure_fin(creneauxDTO.getHeureFin());
    creneaux.setConsultations(
      creneauxDTO.getConsultations().stream()
        .map(ConsultationDto::toEntity)
        .collect(Collectors.toList())
    );

    return creneaux;
  }


  public static List<CreneauxDto> toDtoList(List<Creneaux> creneaux) {
    return creneaux.stream().map(CreneauxDto::toDto).collect(Collectors.toList());
  }

}


