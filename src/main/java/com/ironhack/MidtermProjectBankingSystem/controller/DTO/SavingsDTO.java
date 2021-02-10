package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.math.*;
import java.util.*;

public class SavingsDTO {

    @NotNull(message = "Balance is required")
    private BigDecimal balance;

    @NotNull(message = "Primary Owner Id is required")
    private Long idPrimaryOwner;

    private Optional<Long> idSecondaryOwner;

    @NotNull(message = "Secret key is required")
    private String secretKey;

    @DecimalMax(value = "1000", message = "Minimum balance must be below 1000")
    @DecimalMin(value = "100", message = "Minimum balance must be above 100")
    private BigDecimal minimumBalance = new BigDecimal("1000");

    @DecimalMax(value = "0.5", message = "Max Interest rate must be 0.5")
    @Positive(message = "Interest rate must be positive")
    private BigDecimal interestRate = new BigDecimal("0.0025");

    public SavingsDTO() {
    }

    public SavingsDTO(@NotNull(message = "Balance is required") BigDecimal balance, @NotNull(message = "Primary Owner Id is required") Long idPrimaryOwner, Optional<Long> idSecondaryOwner, @NotNull(message = "Secret key is required") String secretKey, @DecimalMax(value = "1000", message = "Minimum balance must be below 1000") @DecimalMin(value = "100", message = "Minimum balance must be above 100") BigDecimal minimumBalance, @DecimalMax(value = "0.5", message = "Max Interest rate must be 0.5") @Positive(message = "Interest rate must be positive") BigDecimal interestRate) {
        this.balance = balance;
        this.idPrimaryOwner = idPrimaryOwner;
        this.idSecondaryOwner = idSecondaryOwner;
        this.secretKey = secretKey;
        this.minimumBalance = minimumBalance;
        this.interestRate = interestRate;
    }

    public Long getIdPrimaryOwner() {
        return idPrimaryOwner;
    }

    public void setIdPrimaryOwner(Long idPrimaryOwner) {
        this.idPrimaryOwner = idPrimaryOwner;
    }

    public Optional<Long> getIdSecondaryOwner() {
        return idSecondaryOwner;
    }

    public void setIdSecondaryOwner(Optional<Long> idSecondaryOwner) {
        this.idSecondaryOwner = idSecondaryOwner;
    }

    public String getSecretKey() {
        return secretKey;
    }

    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public BigDecimal getMinimumBalance() {
        return minimumBalance;
    }

    public void setMinimumBalance(BigDecimal minimumBalance) {
        this.minimumBalance = minimumBalance;
    }

    public BigDecimal getInterestRate() {
        return interestRate;
    }

    public void setInterestRate(BigDecimal interestRate) {
        this.interestRate = interestRate;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }
}
