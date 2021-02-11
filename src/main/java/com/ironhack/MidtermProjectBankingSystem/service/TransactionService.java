package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction create (TransactionDTO transactionDTO, UserDetails userDetails) {

        Optional<Account> originAccountOp = accountRepository.findById(transactionDTO.getOrigenAccountId());
        Optional<Account> destinationAccountOp = accountRepository.findById(transactionDTO.getDestinationAccountId());
        if (originAccountOp.isPresent() && destinationAccountOp.isPresent()) {

            Account originAccount = originAccountOp.get();
            Account destinationAccount = destinationAccountOp.get();

            Money amount = transactionDTO.getAmount();
            String nameOwnerDestinationAccount = transactionDTO.getNameOwnerDestinationAccount();

            String userName = originAccount.getPrimaryOwner().getUsername();
            String password = originAccount.getPrimaryOwner().getPassword();

            Money auxBalance = new Money(originAccount.getBalance().getAmount());

            Boolean userBool = userName.equals(userDetails.getUsername()) && password.equals(userDetails.getPassword());
            Boolean nameBool = destinationAccount.getPrimaryOwner().getName().equals(nameOwnerDestinationAccount) ||
                    destinationAccount.getSecondaryOwner().getName().equals(nameOwnerDestinationAccount);
            Boolean enoughBalance = auxBalance.decreaseAmount(amount).compareTo(new BigDecimal("0.00"))>-1;

            if (userBool && nameBool && enoughBalance) {

                if (originAccount instanceof Savings) {
                    Savings saving = (Savings) originAccount;

                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(saving.getMinimumBalance().getAmount())<0) {
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(saving.getPenaltyFee())));
                    }

                }else if (originAccount instanceof Checking) {
                    Checking checking = (Checking) originAccount;
                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(checking.getMinimumBalance().getAmount())<0){
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(checking.getPenaltyFee())));
                    }
                }else{

                    originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(amount)));
                }

                destinationAccount.setBalance(new Money(destinationAccount.getBalance().increaseAmount(amount)));

                Transaction transaction = new Transaction();
                transaction.setDescription(transactionDTO.getDescription());
                transaction.setAmount(transactionDTO.getAmount());
                transaction.setTransactionDate(new Date());
                transaction.setOrigenAccount(originAccount);
                transaction.setDestinationAccount(destinationAccount);
                return transactionRepository.save(transaction);
            } else if (!userBool) {
                throw new IllegalArgumentException("Incorrect username or password");
            } else if (!nameBool){
                throw new IllegalArgumentException("The given name doest not match any account");
            }else{
                throw new IllegalArgumentException("There is not enough money to make de transaction");
            }
        }else{
            throw new IllegalArgumentException("The given Account id doest not match any account");
        }
    }
}


