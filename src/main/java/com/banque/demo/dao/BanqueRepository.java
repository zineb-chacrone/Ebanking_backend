package com.banque.demo.dao;

import com.banque.demo.entities.Banque;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BanqueRepository extends JpaRepository<Banque,Long> {
}
