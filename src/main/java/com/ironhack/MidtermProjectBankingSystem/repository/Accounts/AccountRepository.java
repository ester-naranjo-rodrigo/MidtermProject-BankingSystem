package com.ironhack.MidtermProjectBankingSystem.repository.Accounts;


import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {

    List<Account> findByPrimaryOwner(AccountHolder accountHolder);

}
