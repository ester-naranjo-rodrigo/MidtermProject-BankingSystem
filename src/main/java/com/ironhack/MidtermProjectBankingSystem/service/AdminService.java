package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.math.*;
import java.time.*;
import java.time.temporal.*;
import java.util.*;
import static java.time.temporal.ChronoUnit.DAYS;

@Service
public class AdminService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    SavingsRepository savingsRepository;

    @Autowired
    CreditCardRepository creditCardRepository;

    @Autowired
    StudentCheckingRepository studentCheckingRepository;

    @Autowired
    CheckingRepository checkingRepository;


}
