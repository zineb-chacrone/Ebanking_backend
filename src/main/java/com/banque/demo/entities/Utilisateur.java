package com.banque.demo.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import javax.persistence.*;
import java.io.Serializable;


@Entity @Data @AllArgsConstructor @NoArgsConstructor @ToString
@Inheritance(strategy = InheritanceType.JOINED)
public  abstract class  Utilisateur implements Serializable {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(unique = true,nullable = false)
    private String username;
    @Column(nullable = false)
    private String password;
    @Column(unique = true)
    private String email;
    private String role;
    private boolean activated=true;
}
