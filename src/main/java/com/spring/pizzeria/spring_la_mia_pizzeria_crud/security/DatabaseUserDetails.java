package com.spring.pizzeria.spring_la_mia_pizzeria_crud.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.Role;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.User;

// implementiamo l'interfaccia UserDetails che ci dice esattamente cosa deve fare
// l'utente preso dal db per essere gestito da Spring Security
// l'implementazione ovviamente sta a noi
public class DatabaseUserDetails implements UserDetails {
    private final Integer id;
    private final String username;
    private final String password;
    private final Set<GrantedAuthority> authorities;

    // alla costruzione degli UserDetails prendiamo quindi
    // l'utente recuperato dal db dalla repository
    public DatabaseUserDetails(User user) {
        this.id = user.getId();
        this.username = user.getUsername();
        this.password = user.getPassword();

        this.authorities = new HashSet<GrantedAuthority>();
        for (Role role : user.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getName()));
        }
    }

    // getters
    public Integer getId() {
      return this.id;
    }

    public String getUsername() {
      return this.username;
    }

    public String getPassword() {
      return this.password;
    }

    public Set<GrantedAuthority> getAuthorities() {
      return this.authorities;
    }

}
