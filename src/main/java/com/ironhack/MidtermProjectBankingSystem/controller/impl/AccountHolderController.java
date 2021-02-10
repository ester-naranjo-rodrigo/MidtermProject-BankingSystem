package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.controller.interfaces.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class AccountHolderController implements IAccountHolderController {

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private AccountService accountService;

    @Autowired
    private TransactionService transactionService;

    @GetMapping("/accounts")
    public List<Account> findAccountsByAccountHolderId(@AuthenticationPrincipal UserDetails userDetails) {
        return accountService.getAllAccountsByUsername(userDetails.getUsername());
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid TransactionDTO transactionDTO) {
        return transactionService.create(transactionDTO);
    }
}
