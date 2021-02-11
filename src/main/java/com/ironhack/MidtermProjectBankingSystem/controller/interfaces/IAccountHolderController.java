package com.ironhack.MidtermProjectBankingSystem.controller.interfaces;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.security.core.userdetails.*;

import java.util.*;

public interface IAccountHolderController {
    public List<Account> findAccountsByAccountHolderId(UserDetails userDetails);
    public Transaction create (TransactionDTO transactionDTO, UserDetails userDetails);
}
