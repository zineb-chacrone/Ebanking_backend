package com.banque.demo.dao;

import com.banque.demo.entities.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface ClientRepository extends JpaRepository<Client,Long> {
    public Client findByUsername(String username);
}
