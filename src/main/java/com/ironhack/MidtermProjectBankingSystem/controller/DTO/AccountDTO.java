package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.enums.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;

public class AccountDTO {
    @NotNull(message = "Please, specify the amount to transfer")
    @Positive (message = "The amount must be positive")
    private BigDecimal amount;
    @NotNull (message = "Please, specify the id of the account")
    private Long id;
    @NotNull (message = "Please, specify the secretKey of the account")
    private String secretKey;
    @Enumerated(EnumType.STRING)
    @NotNull
    private TransactionType transactionType;
    public AccountDTO(){
    }
    public AccountDTO(@NotNull(message = "Please, specify the amount to transfer") BigDecimal amount,
                      @NotNull(message = "Please, specify the id of the account") Long id,
                      @NotNull(message = "Please, specify the secretKey of the account") String secretKey) {
        this.amount = amount;
        this.id = id;
        this.secretKey = secretKey;
    }
    public BigDecimal getAmount() {
        return amount;
    }
    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }
    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getSecretKey() {
        return secretKey;
    }
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
    public TransactionType getTransactionType() {
        return transactionType;
    }
    public void setTransactionType(TransactionType transactionType) {
        this.transactionType = transactionType;
    }
}
