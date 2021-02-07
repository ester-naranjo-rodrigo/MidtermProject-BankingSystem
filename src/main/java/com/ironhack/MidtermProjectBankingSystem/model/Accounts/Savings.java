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
public class Savings extends Account{

    @DecimalMax(value = "0.5", message = "Max Interest rate must be 0.5")
    @Positive(message = "Interest rate must be positive")
    private BigDecimal interestRate = new BigDecimal("0.0025");

    @NotNull(message = "Secret key required")
    protected String secretKey;

    @Enumerated(value = EnumType.STRING)
    protected Status status;

    @DecimalMax(value = "1000", message = "Minimum balance must be below 1000")
    @DecimalMin(value = "100", message = "Minimum balance must be above 100")
    private BigDecimal minimumBalance;

}
