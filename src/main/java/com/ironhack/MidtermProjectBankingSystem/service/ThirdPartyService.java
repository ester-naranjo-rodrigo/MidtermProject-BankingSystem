package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

@Service
public class ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public ThirdParty createThirdParty (ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(thirdPartyDTO.getName());
        thirdParty.setHashedKey(thirdPartyDTO.getHashKey());
        return thirdPartyRepository.save(thirdParty);
    }

    public ThirdParty createThirdPartya (ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(thirdPartyDTO.getName());
        thirdParty.setHashedKey(thirdPartyDTO.getHashKey());
        return thirdPartyRepository.save(thirdParty);
    }
}
