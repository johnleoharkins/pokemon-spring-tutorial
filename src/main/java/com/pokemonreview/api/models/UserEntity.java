package com.pokemonreview.api.models;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "users")
@Data
@NoArgsConstructor
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String username;
    private String password;
    // many to many, jpa will create a join table between user_id and role_id/ user can have many different roles. Dont want user to be pulled with the role so the many to many relationship need not be defined in the Role class
    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL) // when loaded always want roles to be shown with user
    @JoinTable(name = "user_roles", joinColumns = @JoinColumn(name = "user_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name="role_id", referencedColumnName = "id"))
    private List<Role> roles = new ArrayList<>();
}
