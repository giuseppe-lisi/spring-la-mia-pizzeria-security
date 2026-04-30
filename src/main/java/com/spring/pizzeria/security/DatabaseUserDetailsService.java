package com.spring.pizzeria.security;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.User;

@Service
public class DatabaseUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepository usersRepository;

    @Override
    // vogliamo restituire al nostro DoaAuthenticationProvider un oggetto che Spring Security
    // sia in grado di gestire: un UserDetails... ma dal db noi possiamo soltanto andare a recuperare uno User
    // servirà quindi implementare una classe DatabaseUserDetails che prende quello che ci serve dallo User
    // che abbiamo pescato dal database e lo trasformi in un oggetto che l'auth provider può leggere
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // recuperiamo il db dall'utente tramite la repo usando l'username
        Optional<User> userOpt = usersRepository.findByUsername(username);

        if (userOpt.isEmpty()) {
            throw new UsernameNotFoundException("There are no users with username " + username);
        }
        
        // se esiste, resituiamo un oggetto con i dati dell'utente leggibile da Spring Security
        return new DatabaseUserDetails(userOpt.get()); 
    }
}
