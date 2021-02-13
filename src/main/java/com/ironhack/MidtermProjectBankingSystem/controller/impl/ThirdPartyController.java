package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.controller.interfaces.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import com.ironhack.MidtermProjectBankingSystem.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;

import javax.validation.*;
import java.util.*;

@RestController
public class ThirdPartyController implements IThirdPartyController {

    @Autowired
    private AccountService accountService;

    @Autowired
    private ThirdPartyService thirdPartyService;

    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    @GetMapping("/check/thirdParties")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAll() {
        return thirdPartyRepository.findAll();
    }

    @GetMapping("/check/thirdParty/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getById(@PathVariable long id) {
        return thirdPartyService.getById(id);
    }

    @PatchMapping("/thirdPartyOperation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void thirdPartyOperation (@RequestParam Integer hashedKey, @RequestBody OperationThirdPartyDTO operationThirdPartyDTO) {
        accountService.operationThirdParty(hashedKey, operationThirdPartyDTO);
    }
}
