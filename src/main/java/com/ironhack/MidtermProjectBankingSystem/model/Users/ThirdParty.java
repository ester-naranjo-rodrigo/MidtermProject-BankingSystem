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
}
