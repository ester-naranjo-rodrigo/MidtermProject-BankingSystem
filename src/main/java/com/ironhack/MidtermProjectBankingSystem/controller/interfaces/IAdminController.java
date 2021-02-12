package com.ironhack.MidtermProjectBankingSystem.controller.interfaces;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.security.core.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

public interface IAdminController {
    public Savings createAccountSavings(SavingsDTO savingPostDTO);
    public CreditCard createAccountCreditCard(CreditCardDTO creditCardDTO);
    public Object createAccountChecking(CheckingDTO checkingDTO);
    public AccountHolder createAccountHolder(AccountHolderDTO accountHolderDTO);
    public ThirdParty createThirdParty(ThirdPartyDTO thirdPartyDTO);
    public List<AccountHolder> findAccountHolders();
    public void updateStatus(long id, StatusDTO statusDTO);
    public void updateBalance (Long id, BalanceDTO balance);

}
