package com.ironhack.MidtermProjectBankingSystem.model.Users;

import com.fasterxml.jackson.annotation.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @Column(unique = true)
    private String username;

    private String password;

    @OneToMany(mappedBy = "user",fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JsonIgnore
    protected Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String username, String password, Set<Role> roles) {
        this.username = username;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
