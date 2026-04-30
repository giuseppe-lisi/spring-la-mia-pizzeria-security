package com.spring.pizzeria.spring_la_mia_pizzeria_crud.security;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import com.spring.pizzeria.spring_la_mia_pizzeria_crud.models.User;

// questa repo viene usata dal service per andare a prendere l'utente che ci serve
public interface UsersRepository extends JpaRepository<User, Integer> {
    Optional<User> findByUsername(String username);
}
