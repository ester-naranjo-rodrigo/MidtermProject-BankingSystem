package com.ironhack.MidtermProjectBankingSystem.model.Accounts;

import com.ironhack.MidtermProjectBankingSystem.enums.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.math.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Checking extends Account{

    @NotNull(message = "Secret key can not be null")
    private String secretKey;

    private final BigDecimal monthlyMaintenanceFee = new BigDecimal("12");

    private final BigDecimal minimumBalance = new BigDecimal("250");

    @Enumerated(value = EnumType.STRING)
    private Status status;

}
