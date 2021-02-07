package com.ironhack.MidtermProjectBankingSystem.model.Users;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class ThirdParty extends User{
    @NotNull
    private String hashedKey;
}
