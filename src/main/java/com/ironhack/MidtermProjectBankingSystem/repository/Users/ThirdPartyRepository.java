package com.ironhack.MidtermProjectBankingSystem.repository.Users;

import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import java.util.*;

@Repository
public interface ThirdPartyRepository extends JpaRepository<ThirdParty, Long> {
    public Optional<ThirdParty> findByHashedKey(Integer hashedKey);

}
