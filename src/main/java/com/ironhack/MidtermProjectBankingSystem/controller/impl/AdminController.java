package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.controller.interfaces.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import com.ironhack.MidtermProjectBankingSystem.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class AdminController implements IAdminController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private AccountHolderService accountHolderService;

    @Autowired
    private AccountHolderRepository accountHolderRepository;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @GetMapping("/accountHolders")
    public List<AccountHolder> findAccountHolders() {
        return accountHolderRepository.findAll();
    }

    @PostMapping("/create/savings")
    @ResponseStatus(HttpStatus.CREATED)
    public Savings createAccountSavings(@RequestBody @Valid SavingsDTO newAccount) {
        return accountService.createSaving(newAccount);
    }

    @PostMapping("/create/creditcard")
    @ResponseStatus(HttpStatus.CREATED)
    public CreditCard createAccountCreditCard(@RequestBody @Valid CreditCardDTO creditCard) {
        return accountService.createCreditCard(creditCard);
    }

    @PostMapping("/create/checking")
    @ResponseStatus(HttpStatus.CREATED)
    public Object createAccountChecking(@RequestBody @Valid CheckingDTO checking) {
        return accountService.createChecking(checking);
    }

    @PostMapping("/create/accountHolder")
    @ResponseStatus(HttpStatus.CREATED)
    public AccountHolder createAccountHolder(@RequestBody @Valid AccountHolderDTO accountHolderDTO) {
        return accountHolderService.createAccountHolder(accountHolderDTO);
    }

    @PostMapping("/create/thirdParty")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty createAccountHolder(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    @PatchMapping("/account/change-status/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateStatus(@PathVariable long id, @RequestBody @Valid StatusDTO statusDTO) {
        accountService.updateStatus(id, statusDTO.getStatus());
    }

    @PatchMapping("/updateBalance/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateBalance (@PathVariable Long id, @RequestBody BalanceDTO balance) {
        accountService.updateBalance(id, balance.getBalance());
    }


}
