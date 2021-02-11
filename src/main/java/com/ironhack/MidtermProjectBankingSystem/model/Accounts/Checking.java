package com.ironhack.MidtermProjectBankingSystem.model.Accounts;

import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.*;
import javax.validation.constraints.*;
import java.math.*;
import java.time.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Checking extends Account{

    private String secretKey;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_monthly_maintenance_fee")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_monthly_maintenance_fee"))
    })
    private final Money monthlyMaintenanceFee = new Money(new BigDecimal("12"));

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance"))
    })
    private final Money minimumBalance = new Money(new BigDecimal("250"));

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public Checking() {
    }

    public Checking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, String secretKey, Status status) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.secretKey = secretKey;
        this.status = status;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Money getMonthlyMaintenanceFee() {
        return monthlyMaintenanceFee;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
