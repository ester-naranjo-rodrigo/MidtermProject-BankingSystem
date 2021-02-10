package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

import java.time.*;
import java.util.*;

@Service
public class AccountService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    AccountRepository accountRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    CheckingRepository checkingRepository;

    public List<Account> getAllAccountsByUsername(String username) {
        AccountHolder user = accountHolderRepository.findByUsername(username);
        List<Account> accounts = accountRepository.findByPrimaryOwner(user);
        return accounts;
    }

    public Account getAccountById(Long id) {
        Account account = accountRepository.findById(id).get();
        return account;
    }

    public Savings createSaving(SavingsDTO newAccount) {
        Savings savings = new Savings();
        savings.setSecretKey(newAccount.getSecretKey());
        savings.setMinimumBalance(new Money(newAccount.getMinimumBalance()));
        savings.setInterestRate(newAccount.getInterestRate());
        savings.setStatus(Status.ACTIVE);
        savings.setBalance(new Money(newAccount.getBalance()));
        savings.setDateOfCreation(new Date());
        savings.setPrimaryOwner(accountHolderRepository.findById(newAccount.getIdPrimaryOwner()).get());
        if(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).isEmpty() ){
        }else{
            savings.setSecondaryOwner(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).get());
        }
        accountRepository.save(savings);
        return savingsRepository.save(savings);
        }

    public CreditCard createCreditCard(CreditCardDTO newAccount) {
        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(new Money(newAccount.getCreditLimit()));
        creditCard.setInterestRate(newAccount.getInterestRate());
        creditCard.setBalance(new Money(newAccount.getBalance()));
        creditCard.setPrimaryOwner(accountHolderRepository.findById(newAccount.getIdPrimaryOwner()).get());
        if(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).isEmpty() ){
        }else{
            creditCard.setSecondaryOwner(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).get());
        }
        accountRepository.save(creditCard);
        return creditCardRepository.save(creditCard);
        }


    public Object createChecking(CheckingDTO newAccount) {

        Integer age = Period.between(accountHolderRepository.findById(newAccount.getIdPrimaryOwner()).get().getDateOfBirth(),
                LocalDate.now()).getYears();

        if(age < 24){
            StudentChecking studentChecking = new StudentChecking();
            studentChecking.setStatus(Status.ACTIVE);
            studentChecking.setSecretKey(newAccount.getSecretKey());
            studentChecking.setBalance(new Money(newAccount.getBalance()));
            studentChecking.setDateOfCreation(new Date());
            studentChecking.setPrimaryOwner(accountHolderRepository.findById(newAccount.getIdPrimaryOwner()).get());
            if(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).isEmpty() ){
            }else{
                studentChecking.setSecondaryOwner(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).get());
            }
            accountRepository.save(studentChecking);
            return studentCheckingRepository.save(studentChecking);
        }
        else {
            Checking checking = new Checking();
            checking.setStatus(Status.ACTIVE);
            checking.setSecretKey(newAccount.getSecretKey());
            checking.setBalance(new Money(newAccount.getBalance()));
            checking.setDateOfCreation(new Date());
            checking.setPrimaryOwner(accountHolderRepository.findById(newAccount.getIdPrimaryOwner()).get());
            if(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).isEmpty() ){
            }else{
                checking.setSecondaryOwner(accountHolderRepository.findById(newAccount.getIdSecondaryOwner().get()).get());
            }
            accountRepository.save(checking);
            return checkingRepository.save(checking);
        }
    }

    public void updateStatus(long id, Status status) {
        Optional<Account> account = accountRepository.findById(id);
        if (account.isPresent()) {
            if (account.get() instanceof CreditCard) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Credit Card do not have a state");
            }
            if (account.get() instanceof Savings) {
                ((Savings) account.get()).setStatus(status);
                accountRepository.save(account.get());
                savingsRepository.save((Savings) account.get());
            }
            if (account.get() instanceof Checking) {
                ((Checking) account.get()).setStatus(status);
                accountRepository.save(account.get());
                checkingRepository.save((Checking) account.get());
            }
            if (account.get() instanceof StudentChecking) {
                ((StudentChecking) account.get()).setStatus(status);
                accountRepository.save(account.get());
                studentCheckingRepository.save((StudentChecking) account.get());
            }
        } else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

    public void updateBalance(Long id, Money balance) {
        Optional<Account> account = accountRepository.findById(id);
        if(account.isPresent()){
            account.get().setBalance(balance);
            accountRepository.save(account.get());
        }
        else {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Account not found");
        }
    }

}


