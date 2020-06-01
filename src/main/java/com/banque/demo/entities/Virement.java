package com.banque.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Virement implements Serializable {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Date date;
    @Column(length = 40)
    private String type;
    private long montant;
    private long numcomptebenf;
    @ManyToOne
    private Compte compte;
    @ManyToOne
    private  Client client;

    public void Retrait(Compte compte,long montant){

        compte.setSolde(compte.getSolde()-montant);

    }

    public void Verser(Compte compte,long montant){

        compte.setSolde(compte.getSolde()+montant);
    }

    public void Virer(Compte compte1,Compte compte2,long montant){

        Retrait(compte1,montant);
        Verser(compte2,montant);

    }

}

