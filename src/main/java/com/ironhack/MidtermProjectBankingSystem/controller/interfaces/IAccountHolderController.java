package com.ironhack.MidtermProjectBankingSystem.controller.interfaces;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import java.util.*;

public interface IAccountHolderController {
    public List<Account> findAllAccountsByAccountHolderAuth(UserDetails userDetails);
    public Optional<Account> findAccountsById(Long id);
    public Transaction transferMoney (TransactionDTO transactionDTO, UserDetails userDetails);
}
