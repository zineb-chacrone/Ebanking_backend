package com.banque.demo.dao;

import com.banque.demo.entities.Agence;
import com.banque.demo.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
@RepositoryRestResource
@CrossOrigin("*")
public interface AgentRepository extends JpaRepository<Agent,Long> {
    public List<Agent> findAgentByAgence(Agence agence);

    public Agent findByUsername(String username);


}
