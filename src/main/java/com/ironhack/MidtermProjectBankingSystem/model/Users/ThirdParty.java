package com.ironhack.MidtermProjectBankingSystem.model.Users;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
@DynamicUpdate
public class ThirdParty{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private Long id;

    @NotNull
    private String name;

    @NotNull
    private String hashedKey;

    public ThirdParty() {
    }

    public ThirdParty(@NotNull String name, @NotNull String hashedKey) {
        this.name = name;
        this.hashedKey = hashedKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHashedKey() {
        return hashedKey;
    }

    public void setHashedKey(String hashedKey) {
        this.hashedKey = hashedKey;
    }
}

