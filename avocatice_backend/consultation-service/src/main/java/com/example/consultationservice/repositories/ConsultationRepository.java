package com.example.consultationservice.repositories;

import com.example.consultationservice.entities.Consultation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;


@Repository
public interface ConsultationRepository extends JpaRepository<Consultation, Long> {

    @Modifying
    @Transactional
    @Query("UPDATE Consultation c SET c.reponse = 'true' WHERE c.id = :id")
    public void validerConsultation(@Param("id") Long id);
}
