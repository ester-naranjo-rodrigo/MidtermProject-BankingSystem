package com.ironhack.MidtermProjectBankingSystem.controller.interfaces;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

public interface IThirdPartyController {
    public List<ThirdParty> getAll();
    public ThirdParty getById(long id);
    public void update (Integer hashedKey, AccountDTO accountDTO);
}
