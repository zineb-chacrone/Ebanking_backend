package com.banque.demo.dao;

import com.banque.demo.entities.Agence;
import com.banque.demo.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AgentRepository extends JpaRepository<Agent,Long> {
    public List<Agent> findAgentByAgence(Agence agence);

}
