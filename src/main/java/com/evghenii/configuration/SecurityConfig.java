package com.evghenii.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http
               .authorizeRequests()
               .antMatchers("/login*")
               .permitAll()
               .anyRequest()
               .authenticated()
               .and()
               .formLogin()
               .defaultSuccessUrl("/mvc/person/print")
               .and()
               .logout();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
      auth
              .inMemoryAuthentication()
              .passwordEncoder(passwordEncoder())
              .withUser("John")
              .password("$2a$10$Q.PkJ9zlP.3W6kE0cOyutu1NZq5jnjO6BM2aEX0vFcY9k5Lzt4dNa")
              .roles("USER")
              .and()
              .withUser("Ben")
              .password("$2a$10$qIMka4w6DhQWrXajNeZFZuqRSU5sllkbfK0KQMYn.X4RRBdFYVZGO")
              .roles("USER", "ADMIN");


    }
}
