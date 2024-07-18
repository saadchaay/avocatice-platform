package com.example.usersservice.repositories;

import com.example.usersservice.entities.Avocat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AvocatRepository extends JpaRepository<Avocat, Long> {
}
