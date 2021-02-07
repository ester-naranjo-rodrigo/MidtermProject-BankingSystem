package com.ironhack.MidtermProjectBankingSystem.model.Accounts;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.math.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class CreditCard extends Account{

    @DecimalMax(value = "100000")
    @DecimalMin(value = "100")
    private BigDecimal creditLimit = new BigDecimal("100");

    @DecimalMin(value = "0.1")
    @DecimalMax(value = "0.2", inclusive = false)
    private BigDecimal interestRate = new BigDecimal("0.2");

}
