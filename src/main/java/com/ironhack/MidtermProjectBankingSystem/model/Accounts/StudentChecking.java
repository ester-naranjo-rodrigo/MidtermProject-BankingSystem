package com.ironhack.MidtermProjectBankingSystem.model.Accounts;

import com.ironhack.MidtermProjectBankingSystem.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class StudentChecking extends Account{

    @NotNull(message = "Secret key can not be null")
    protected String secretKey;

    @Enumerated(value = EnumType.STRING)
    protected Status status;
}
