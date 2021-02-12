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

import java.util.*;

@Service
public class AccountHolderService {

    @Autowired
    AccountHolderRepository accountHolderRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO) {
        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName(accountHolderDTO.getName());
        accountHolder.setDateOfBirth(accountHolderDTO.getDateOfBirth());
        accountHolder.setPrimaryAddress(accountHolderDTO.getPrimaryAddress());
        accountHolder.setMailingAddress(accountHolderDTO.getMailingAddress());
        accountHolder.setUsername(accountHolderDTO.getUsername());
        accountHolder.setPassword(accountHolderDTO.getPassword());

        userRepository.save(accountHolder);

        Role role = new Role("ACCOUNTHOLDER", accountHolder);
        roleRepository.save(role);

        return accountHolderRepository.save(accountHolder);
    }

}
