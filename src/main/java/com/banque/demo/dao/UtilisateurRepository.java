package com.banque.demo.dao;

import com.banque.demo.entities.Utilisateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UtilisateurRepository extends JpaRepository<Utilisateur,Long> {
    public Utilisateur findByUsername(String s);
}
