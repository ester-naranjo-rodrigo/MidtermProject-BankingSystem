package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.*;

public class TransactionDTO {

    @NotNull
    private Long origenAccountId;
    @NotNull
    private Long destinationAccountId;
    @NotNull
    private String description;
    @NotNull
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "transaction_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "transaction_currency"))
    })
    private Money amount;
    private Date transactionDate;
    private String nameOwnerDestinationAccount;

    public TransactionDTO(){
    }

    public TransactionDTO(@NotNull Long origenAccountId, @NotNull Long destinationAccountId, @NotNull String description, @NotNull Money amount, String nameOwnerDestinationAccount) {
        this.origenAccountId = origenAccountId;
        this.destinationAccountId = destinationAccountId;
        this.description = description;
        this.amount = amount;
        this.transactionDate = new Date();
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;
    }

    public Long getOrigenAccountId() {
        return origenAccountId;
    }
    public void setOrigenAccountId(Long origenAccountId) {
        this.origenAccountId = origenAccountId;
    }
    public Long getDestinationAccountId() {
        return destinationAccountId;
    }
    public void setDestinationAccountId(Long destinationAccountId) {
        this.destinationAccountId = destinationAccountId;
    }
    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }
    public Money getAmount() {
        return amount;
    }
    public void setAmount(Money amount) {
        this.amount = amount;
    }
    public Date getTransactionDate() {
        return transactionDate;
    }
    public void setTransactionDate(Date transactionDate) {
        this.transactionDate = transactionDate;
    }
    public String getNameOwnerDestinationAccount() {
        return nameOwnerDestinationAccount;
    }
    public void setNameOwnerDestinationAccount(String nameOwnerDestinationAccount) {
        this.nameOwnerDestinationAccount = nameOwnerDestinationAccount;
    }
}
