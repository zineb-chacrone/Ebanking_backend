package com.banque.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Collection;

@Entity
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Client extends Utilisateur implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Column(length = 40)
    private String nom;
    @Column(length = 40)
    private String prenom;
    @Column(unique = true,length = 40)
    private String email;
    @Column(unique = true,length = 40)
    private String cin;
    @Column(length = 40)
    private String status="desactive";
    @OneToMany(mappedBy="client")
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Compte> comptes;
    @OneToMany(mappedBy = "client")
   @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Virement> virements;
    @ManyToOne
    private Agence agence;
    @ManyToOne
    private Agent agent;
    @OneToMany(mappedBy = "client")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Message> messages;
}
