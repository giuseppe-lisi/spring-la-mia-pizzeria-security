package com.spring.pizzeria.spring_la_mia_pizzeria_crud.models;

import java.util.Set;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "roles")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "Role name cannot be empty")
    private String name;

    @ManyToMany(mappedBy = "roles", fetch = FetchType.EAGER)
    @JsonBackReference
    private Set<User> users;

    // get set
    public Integer getId() {
      return this.id;
    }
    public void setId(Integer value) {
      this.id = value;
    }

    public String getName() {
      return this.name;
    }
    public void setName(String value) {
      this.name = value;
    }

    public Set<User> getUsers() {
      return this.users;
    }
    public void setUsers(Set<User> value) {
      this.users = value;
    }
}
