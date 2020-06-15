package com.banque.demo.web;


import com.banque.demo.dao.AdminRepository;
import com.banque.demo.entities.Adminstrateur;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

@Controller
public class ControllerLogin {
    Logger logger= LoggerFactory.getLogger(ControllerLogin.class);

    @Autowired

    private AdminRepository adminRepository;
    private Adminstrateur currentAdmin;
    private PasswordEncoder passwordEncoder;

    public ControllerLogin(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @GetMapping("admin/settings")
    public String settings(Principal principal, Model model){
String username=principal.getName();
        System.out.println(username);
        Adminstrateur adminstrateur=adminRepository.findAdminByUsername(username);
        currentAdmin=adminstrateur;
        System.out.println("admin object :"+adminstrateur.getNom());
        model.addAttribute("admin",adminstrateur);
        return "settings";
    }
    @PostMapping("/admin/editAdmin")
public String editAdmin(@ModelAttribute("admin") Adminstrateur adminstrateur,Principal principal){
        System.out.println("Hollaa Holla");
        System.out.println(adminstrateur.getPassword());
        System.out.println(adminstrateur.getEmail());
        currentAdmin.setEmail(adminstrateur.getEmail());
        currentAdmin.setPassword(passwordEncoder.encode(adminstrateur.getPassword()));
        adminRepository.save(currentAdmin);
        logger.debug("l'admin :"+principal.getName()+" vient de modifier ses information.");

        return "index";
    }


}
