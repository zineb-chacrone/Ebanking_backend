package com.banque.demo.sec;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class BasicAuthController {
    @CrossOrigin(origins = "http://localhost:4200")
    @GetMapping(path = "/log")
    public AuthenticationBean basicauth() {
        return new AuthenticationBean("You are authenticated");
    }
}
