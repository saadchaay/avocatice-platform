package com.example.usersservice.repositories;

import com.example.usersservice.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AgentRepository extends JpaRepository<Agent, Long> {
}
