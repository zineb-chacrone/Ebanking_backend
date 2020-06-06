package com.banque.demo.sec;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;

@Configuration
@EnableWebSecurity
public class SecurityBanque extends WebSecurityConfigurerAdapter {
@Autowired
private UserPrincipalDetailsService userPrincipalDetailsService;



    @Override
    protected void configure(AuthenticationManagerBuilder auth)  {
auth.authenticationProvider(authenticationProvider());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin().loginPage("/login").permitAll()
                .and().csrf().disable().authorizeRequests().antMatchers("/css/**").permitAll()
                .and().authorizeRequests().antMatchers("/js/**").permitAll()
                .and().authorizeRequests().antMatchers("/images/**").permitAll()
                .and().authorizeRequests().antMatchers("/agent/**").hasRole("AGENT")
                .antMatchers("/client/**").hasRole("CLIENT")
                .antMatchers("/").hasRole("ADMIN")
        .and().authorizeRequests().antMatchers("/admin/**").hasRole("ADMIN")



                .and().logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
                .logoutSuccessUrl("/login").and().httpBasic();


    }

@Bean
    DaoAuthenticationProvider authenticationProvider(){
        DaoAuthenticationProvider provider=new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder());
        provider.setUserDetailsService(userPrincipalDetailsService);
        return provider;
    }

@Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
