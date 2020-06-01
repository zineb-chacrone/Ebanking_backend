package com.banque.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;

@Entity @Data @AllArgsConstructor @NoArgsConstructor
public class Agent  extends Utilisateur{
    private String nomAgent;
    private String telephone;
    private String adresse;
    @ManyToOne
    private Agence agence;
    @OneToMany(mappedBy="agent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Client> clients;
    @OneToMany(mappedBy = "agent")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private Collection<Message> messages;

}
