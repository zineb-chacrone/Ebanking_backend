package com.banque.demo.sec;

import com.banque.demo.dao.UtilisateurRepository;
import com.banque.demo.entities.Utilisateur;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserPrincipalDetailsService implements UserDetailsService {

    private UtilisateurRepository repository;

    public UserPrincipalDetailsService(UtilisateurRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Utilisateur user=repository.findByUsername(s);
        UserPrincipal userPrincipal=new UserPrincipal(user);
        return userPrincipal;
    }
}
