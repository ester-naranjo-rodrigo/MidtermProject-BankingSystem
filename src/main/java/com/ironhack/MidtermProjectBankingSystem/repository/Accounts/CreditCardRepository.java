package com.ironhack.MidtermProjectBankingSystem.repository.Accounts;

import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {
}
