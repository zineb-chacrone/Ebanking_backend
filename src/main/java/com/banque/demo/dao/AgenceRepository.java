package com.banque.demo.dao;


import com.banque.demo.entities.Agence;
import com.banque.demo.entities.Agent;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface AgenceRepository extends JpaRepository<Agence,Long> {
    @Modifying
    @Transactional
    @Query("UPDATE Agence c SET c.nombreAgent=:agence WHERE c.id = :id")
    public int updateAgence(@Param("id") Long id, @Param("agence") int nombre);

    public Agence findAgenceByNomAgence(String nom);

    public Agence findAgenceByAgentsContains(Agent agent);

    @Modifying
    @Transactional
    @Query("UPDATE Agence c SET c=:agence WHERE c.id = :id")
    public int update(@Param("id") Long id, @Param("agence") Agence agence);
}
