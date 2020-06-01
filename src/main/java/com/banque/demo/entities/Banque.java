package com.banque.demo.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
public class Banque  implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String nomBanque;
    public Banque(String nomBanque) {
        this.nomBanque=nomBanque;
    }
    @OneToMany(mappedBy = "banque" ,fetch = FetchType.LAZY)
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private List<Adminstrateur> adminstrateurs=new ArrayList<Adminstrateur>();
    @OneToMany(mappedBy = "banque")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private  Collection<Agence> agences=new ArrayList<Agence>();



}
