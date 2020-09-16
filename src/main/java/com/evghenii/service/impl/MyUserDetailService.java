package com.evghenii.service.impl;

import com.evghenii.domain.Role;
import com.evghenii.domain.User;
import com.evghenii.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MyUserDetailService implements UserDetailsService {

    private final UserRepository repository;

    public MyUserDetailService(UserRepository repository) {
        this.repository = repository;
    }

    @Override
    public UserDetails loadUserByUsername(String name) throws UsernameNotFoundException {

        final User user = repository.findByFirstName(name);

        return buildUserForAuthentication(user);
    }

    private List<GrantedAuthority> getRoles(Set<Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(role.getRole())).collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication(User user) {
        final List<GrantedAuthority> roles = getRoles(user.getRoles());

        return new org.springframework.security.core.userdetails.User(user.getFirstName(),
                user.getPassword(),
                user.isActive(),
                true,
                true,
                true,
                roles);
    }
}
