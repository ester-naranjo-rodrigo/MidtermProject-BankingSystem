package com.ironhack.MidtermProjectBankingSystem.service;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.stereotype.*;
import org.springframework.web.server.*;

@Service
public class ThirdPartyService {

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    public ThirdParty getById(long id) {
        if(!thirdPartyRepository.existsById(id)){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Third Party user not found");
        }
        return thirdPartyRepository.findById(id).get();
    }

    public ThirdParty createThirdParty (ThirdPartyDTO thirdPartyDTO) {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName(thirdPartyDTO.getName());
        thirdParty.setHashedKey(thirdPartyDTO.getHashedKey());
        return thirdPartyRepository.save(thirdParty);
    }
}
