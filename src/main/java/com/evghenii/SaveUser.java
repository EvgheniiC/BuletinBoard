package com.evghenii;

import com.evghenii.configuration.ConfigApp;
import com.evghenii.domain.Role;
import com.evghenii.domain.User;
import com.evghenii.repository.UserRepository;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

public class SaveUser {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(ConfigApp.class);

        final UserRepository repository = context.getBean(UserRepository.class);

        final Role admin = new Role("ROLE_ADMIN");
        final Role simpleUser = new Role("ROLE_USER");

        final Set<Role> johnRoles = new HashSet<>(Arrays.asList(admin, simpleUser));
        final Set<Role> jackRoles = new HashSet<>(Arrays.asList(simpleUser));


        final User john = new User("John", "Skeet", true, "123456", johnRoles);
        final User jack = new User("Jack", "Score", true, "123", jackRoles);

        repository.saveAll(Arrays.asList(john, jack));
    }
}
