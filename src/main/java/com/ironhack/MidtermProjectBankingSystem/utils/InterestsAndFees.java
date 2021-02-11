package com.ironhack.MidtermProjectBankingSystem.utils;

import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import org.springframework.beans.factory.annotation.*;

import java.math.*;
import java.time.*;

public class InterestsAndFees {

    @Autowired
    private CreditCardRepository creditCardRepository;

    public static void addInterestCreditCard(Long id, CreditCardRepository creditCardRepository) {

        CreditCard creditCard = creditCardRepository.findById(id).get();

        Integer monthsSinceCreation = Period.between(LocalDate.now(), creditCard.getDateOfCreation()).getMonths();
        Integer monthsSinceLastAccess = Period.between(LocalDate.now(), creditCard.getDateOfLastAccess()).getMonths();

        BigDecimal interestPerMonth = creditCard.getInterestRate().divide(new BigDecimal(12));

        creditCard.setBalance(new Money(creditCard.getBalance().getAmount().multiply((interestPerMonth.add(new BigDecimal(1))))));
        creditCardRepository.save(creditCard);
    }
}

