package com.banque.demo.sec;

import com.banque.demo.dao.AgenceRepository;
import com.banque.demo.dao.AgentRepository;
import com.banque.demo.dao.ClientRepository;
import com.banque.demo.entities.Agent;
import com.banque.demo.entities.Client;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;


@RestController
public class BasicAuthController {
    @Autowired
    AgentRepository agentRepository;
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/log")
    public AuthenticationBean basicauth(Principal principal) {

        return new AuthenticationBean("hello") ;
    }
}
