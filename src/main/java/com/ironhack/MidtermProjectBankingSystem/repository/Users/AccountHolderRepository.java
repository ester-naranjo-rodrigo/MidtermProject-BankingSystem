package com.ironhack.MidtermProjectBankingSystem.repository.Users;

import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AccountHolderRepository extends JpaRepository<AccountHolder, Long> {
    public AccountHolder findByUsername(String username);
}
