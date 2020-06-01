package com.banque.demo.dao;


import com.banque.demo.entities.Adminstrateur;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdminRepository extends JpaRepository<Adminstrateur,Long> {

    public Adminstrateur findAdminByUsername(String username);
}
