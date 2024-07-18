package com.example.userservice.repositories;

import com.example.userservice.entities.Avocat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Repository
public interface AvocatRepository extends JpaRepository<Avocat, String> {

//    @Query("Select a from Avocat a ")
//    List<Avocat> findAllById(@Param("id") Long id);

//    @Modifying
//    @Transactional
//    @Query("update Avocat a set a.status='VALIDER' where a.id=:id")
//    public void validerAvocat(@Param("id") String id);
    @Modifying
    @Transactional
    @Query("UPDATE Avocat a SET a.status = true WHERE a.id = :id")
    public void validerAvocat(@Param("id") String id);

    Avocat findByEmail(String email);
}
