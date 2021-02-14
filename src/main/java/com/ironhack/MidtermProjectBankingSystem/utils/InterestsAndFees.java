package com.ironhack.MidtermProjectBankingSystem.utils;

import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import org.springframework.beans.factory.annotation.*;

import java.math.*;
import java.time.*;

public class InterestsAndFees {

    public static void addInterestCreditCard(Long id, CreditCardRepository creditCardRepository) {

        CreditCard creditCard = creditCardRepository.findById(id).get();

        Integer monthsSinceCreation = Period.between(LocalDate.now(), creditCard.getDateOfCreation()).getMonths();
        Integer monthsSinceLastAccess = Period.between(LocalDate.now(), creditCard.getDateOfLastAccess()).getMonths();

        BigDecimal interestPerMonth = (creditCard.getInterestRate().divide(new BigDecimal(12), 2, RoundingMode.HALF_UP));
        Integer numberMonths = monthsSinceCreation - monthsSinceLastAccess;
        BigDecimal totalInterest = (interestPerMonth.multiply(new BigDecimal(numberMonths))).add(new BigDecimal(1));

        if (monthsSinceCreation > monthsSinceLastAccess) {
            creditCard.setBalance(new Money(creditCard.getBalance().getAmount().multiply(totalInterest)));
            creditCard.setDateOfLastAccess(LocalDate.now());
            creditCardRepository.save(creditCard);
        }
    }

    public static void addInterestSavings(Long id, SavingsRepository savingsRepository) {

        Savings savings = savingsRepository.findById(id).get();

        Integer yearsSinceCreation = Period.between(LocalDate.now(), savings.getDateOfCreation()).getYears();
        Integer yearsSinceLastAccess = Period.between(LocalDate.now(), savings.getDateOfLastAccess()).getYears();

        BigDecimal interestPerYear = savings.getInterestRate();
        Integer numberYears = yearsSinceCreation - yearsSinceLastAccess;
        BigDecimal totalInterest = (interestPerYear.multiply(new BigDecimal(numberYears))).add(new BigDecimal(1));

        if (yearsSinceCreation > yearsSinceLastAccess) {
            savings.setBalance(new Money(savings.getBalance().getAmount().multiply(totalInterest)));
            savings.setDateOfLastAccess(LocalDate.now());
            savingsRepository.save(savings);
        }
    }
}

