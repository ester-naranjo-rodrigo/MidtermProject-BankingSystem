package com.ironhack.MidtermProjectBankingSystem.utils;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Transaction.*;
import org.springframework.beans.factory.annotation.*;

import java.util.*;

public class FraudChecker {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private AccountRepository accountRepository;

    public boolean oneSecondFraudDetection(TransactionDTO transactionDTO) {
        Account originAccount = accountRepository.findById(transactionDTO.getOrigenAccountId()).get();
        List<Transaction> transactions = originAccount.getSentTransactions();
        Transaction lastTransaction = transactions.get(transactions.size() - 1);
        long secondsBetweenTransactions = (transactionDTO.getTransactionDate().getTime() -
                lastTransaction.getTransactionDate().getTime()) / 1000;
        if (secondsBetweenTransactions <= 1) {
            return true;
        } else {
            return false;
        }
    }

    public boolean numberOfTransactionsFraudDetection(TransactionDTO transactionDTO) {
        Account originAccount = accountRepository.findById(transactionDTO.getOrigenAccountId()).get();
        Integer last24hTransactions = transactionRepository.findTransactionsLast24h(originAccount.getId());
        Integer maxHistoric24hTransactions = transactionRepository.findMaxTransactions24hPeriod(originAccount.getId());
        if (last24hTransactions > 1.5 * maxHistoric24hTransactions) {
            return true;
        } else {
            return false;
        }
    }

}
