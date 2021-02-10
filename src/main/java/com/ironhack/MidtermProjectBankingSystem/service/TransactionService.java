package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Transaction.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.util.*;

@Service
public class TransactionService {

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionRepository transactionRepository;

    public Transaction create (TransactionDTO transactionDTO) {
        //busco si existe el id
        Optional<Account> originAccountOp = accountRepository.findById(transactionDTO.getOrigenAccountId());
        Optional<Account> destinationAccountOp = accountRepository.findById(transactionDTO.getDestinationAccountId());
        if (originAccountOp.isPresent() && destinationAccountOp.isPresent()) {
            //guardo las cuentas, origin y destination
            Account originAccount = originAccountOp.get();
            Account destinationAccount = destinationAccountOp.get();
            //guardo el amount que quiera transferir, el nombre del propietario de la cuenta destino, el username y password que me meten por postman,
            //y luego el username y password de la cuenta origen
            Money amount = transactionDTO.getAmount();
            String nameOwnerDestinationAccount = transactionDTO.getNameOwnerDestinationAccount();
            String userNameOrigin = transactionDTO.getUserNameOriginAccount();
            String passwordOrigin = transactionDTO.getPasswordOriginAccount();
            String userName = originAccount.getPrimaryOwner().getUsername();
            String password = originAccount.getPrimaryOwner().getPassword();
            //guardo el balance del origin account
            Money auxBalance = new Money(originAccount.getBalance().getAmount());
            //guardo en un boolean la comparación entre el username y password. En otro boolean guardo la comparación entre el name del account y el que meto por postman
            //El tercer boolean guarda la comprobación de si al realizar la transferencia, mi balance es mayo o igual a 0
            Boolean userBool = userName.equals(userNameOrigin) && password.equals(passwordOrigin);
            Boolean nameBool = destinationAccount.getPrimaryOwner().getName().equals(nameOwnerDestinationAccount) ||
                    destinationAccount.getSecondaryOwner().getName().equals(nameOwnerDestinationAccount);
            Boolean enoughBalance = auxBalance.decreaseAmount(amount).compareTo(new BigDecimal("0.00"))>-1;
            //si es correcto, hago la transferencia
            if (userBool && nameBool && enoughBalance) {
                //si mi cuenta de origen es del tipo saving, creo un saving para acceder a su minimum balance.
                if (originAccount instanceof Savings) {
                    Savings saving = (Savings) originAccount;
                    //si al realizar la transferencia me quedo por debajo del minimumbalance, aplico el panaltyfee
                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(saving.getMinimumBalance().getAmount())<0) {
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(saving.getPenaltyFee())));
                    }
                    //lo anterior pero en el caso de checking
                }else if (originAccount instanceof Checking) {
                    Checking checking = (Checking) originAccount;
                    if (originAccount.getBalance().decreaseAmount(amount).compareTo(checking.getMinimumBalance().getAmount())<0){
                        originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(checking.getPenaltyFee())));
                    }
                }else{
                    //para cualquier otro tipo de cuenta no hay minimumbalance, simplemente hago la transferencia
                    originAccount.setBalance(new Money(originAccount.getBalance().decreaseAmount(amount)));
                }
                //incremento el balance d ela destination account
                destinationAccount.setBalance(new Money(destinationAccount.getBalance().increaseAmount(amount)));
                //creo una transferencia
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


