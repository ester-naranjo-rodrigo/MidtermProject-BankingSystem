package com.ironhack.MidtermProjectBankingSystem.repository.Users;

import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
