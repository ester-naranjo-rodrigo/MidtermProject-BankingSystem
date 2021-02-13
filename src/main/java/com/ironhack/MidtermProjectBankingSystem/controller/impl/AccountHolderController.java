package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.controller.interfaces.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.access.annotation.*;
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

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping("/accounts")
    public List<Account> findAllAccountsByAccountHolderAuth(@AuthenticationPrincipal UserDetails userDetails) {
        return accountService.getAllAccountsByUsername(userDetails.getUsername());
    }

    @GetMapping("/account/{id}")
    public Optional<Account> findAccountsById(@PathVariable Long id) {
        return accountRepository.findById(id);
    }

    @PostMapping("/transaction")
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction create(@RequestBody @Valid TransactionDTO transactionDTO, @AuthenticationPrincipal UserDetails userDetails) {
        return transactionService.create(transactionDTO, userDetails);
    }
}
