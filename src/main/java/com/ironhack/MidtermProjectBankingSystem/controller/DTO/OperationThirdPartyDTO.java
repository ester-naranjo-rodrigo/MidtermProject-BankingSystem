package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;

public class OperationThirdPartyDTO {

    @NotNull
    private String hashKey;

    @NotNull
    private BigDecimal amount;

    @NotNull
    private Long accountId;

    @NotNull
    private String secretKey;

    public OperationThirdPartyDTO() {
    }

    public OperationThirdPartyDTO(@NotNull String hashKey, @NotNull BigDecimal amount, @NotNull Long accountId, @NotNull String secretKey) {
        this.hashKey = hashKey;
        this.amount = amount;
        this.accountId = accountId;
        this.secretKey = secretKey;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }
}


