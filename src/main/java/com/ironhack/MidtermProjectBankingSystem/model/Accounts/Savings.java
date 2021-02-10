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
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Savings extends Account{

    private BigDecimal interestRate;
    private String secretKey;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_minimum_balance")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_minimum_balance"))
    })
    private Money minimumBalance;

    public Savings() {
    }

    public Savings(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, BigDecimal interestRate, String secretKey, Status status, @Valid Money minimumBalance) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.interestRate = interestRate;
        this.secretKey = secretKey;
        this.status = status;
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Money getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(Money minimumBalance) {
        this.minimumBalance = minimumBalance;
    }
}
