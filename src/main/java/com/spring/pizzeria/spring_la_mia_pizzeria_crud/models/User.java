package com.spring.pizzeria.spring_la_mia_pizzeria_crud.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Username cannot be blank")
    private String username;

    @NotBlank(message = "Please enter a password")
    @Size(min = 8, message = "Your password must be at least 8 characters long")
    private String password;

    @ManyToMany
    @JoinTable(
        name = "user_role",
        joinColumns = @JoinColumn(name="user_id"),
        inverseJoinColumns = @JoinColumn(name="role_id")
    )
    @JsonManagedReference
    private Set<Role> roles;

    // get set
    public Integer getId() {
      return this.id;
    }
    public void setId(Integer value) {
      this.id = value;
    }

    public String getUsername() {
      return this.username;
    }
    public void setUsername(String value) {
      this.username = value;
    }

    public String getPassword() {
      return this.password;
    }
    public void setPassword(String value) {
      this.password = value;
    }

    public Set<Role> getRoles() {
      return this.roles;
    }
    public void setRoles(Set<Role> value) {
      this.roles = value;
    }
}
