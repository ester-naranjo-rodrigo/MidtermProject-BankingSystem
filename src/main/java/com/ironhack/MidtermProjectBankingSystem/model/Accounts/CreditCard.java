package com.ironhack.MidtermProjectBankingSystem.model.Accounts;

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
public class CreditCard extends Account{

    @Embedded
    @Valid
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "amount_credit_limit")),
            @AttributeOverride(name = "currency", column = @Column(name = "currency_credit_limit"))
    })
    private Money creditLimit;
    private BigDecimal interestRate;

    public CreditCard() {
    }

    public CreditCard(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, Date dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, @Valid Money creditLimit, BigDecimal interestRate) {
        super(balance, primaryOwner, secondaryOwner, dateOfCreation, receivedTransactions, sentTransactions);
        this.creditLimit = creditLimit;
        this.interestRate = interestRate;
    }

    public Money getCreditLimit() {
        return creditLimit;
    }

    public void setCreditLimit(Money creditLimit) {
        this.creditLimit = creditLimit;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }
}
