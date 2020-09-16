package com.evghenii.configuration.security;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true, securedEnabled = true)
@ComponentScan(basePackages = "com.evghenii")
@Import(value = EncoderConfiguration.class)
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final UserDetailsService myService;

    private final PasswordEncoder encoder;

    public SecurityConfig(@Qualifier("myUserDetailService") UserDetailsService myService, PasswordEncoder encoder) {
        this.myService = myService;
        this.encoder = encoder;
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
                .csrf().disable()
                .formLogin()
                .defaultSuccessUrl("/mvc/person/print")
                .and()
                .logout();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(myService)
                .passwordEncoder(encoder);
    }

    /*@Override
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


    }*/
}
