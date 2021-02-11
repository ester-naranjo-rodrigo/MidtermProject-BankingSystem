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
import java.time.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class StudentChecking extends Account{

    private String secretKey;

    @Enumerated(value = EnumType.STRING)
    private Status status;

    public StudentChecking() {
    }

    public StudentChecking(Money balance, AccountHolder primaryOwner, AccountHolder secondaryOwner, LocalDate dateOfCreation, List<Transaction> receivedTransactions, List<Transaction> sentTransactions, String secretKey, Status status) {
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

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }
}
