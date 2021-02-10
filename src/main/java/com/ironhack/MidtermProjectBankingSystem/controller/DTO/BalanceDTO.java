package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;

import javax.persistence.*;

public class BalanceDTO {
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name = "amount", column = @Column(name = "balance_amount")),
            @AttributeOverride(name = "currency", column = @Column(name = "balance_currency"))
    })
    private Money balance;
    public BalanceDTO(){
    }
    public BalanceDTO(Money balance) {
        this.balance = balance;
    }
    public Money getBalance() {
        return balance;
    }
    public void setBalance(Money balance) {
        this.balance = balance;
    }
}
