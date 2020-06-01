package com.banque.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Agence implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomAgence;
    private String adresseAgence;
    private Date dateCreation;
    private String ville;
    private int nombreAgent=0;
    @ManyToOne
    private Banque banque ;
    @OneToMany(mappedBy = "agence")
    private List<Agent> agents=new ArrayList<Agent>();

}
