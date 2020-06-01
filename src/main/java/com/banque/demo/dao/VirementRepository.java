package com.banque.demo.dao;

import com.banque.demo.entities.Virement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;


@RepositoryRestResource
public interface VirementRepository extends JpaRepository<Virement,Long> {


}
