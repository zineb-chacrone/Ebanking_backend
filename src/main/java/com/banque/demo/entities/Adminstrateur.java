package com.banque.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString

public class Adminstrateur extends Utilisateur {
    private String nom;
    private String prenom;
    @ManyToOne
    private Banque banque;


}
