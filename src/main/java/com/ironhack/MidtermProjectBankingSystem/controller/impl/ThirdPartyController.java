package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.controller.interfaces.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
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

    @GetMapping("/check/third-party")
    @ResponseStatus(HttpStatus.OK)
    public List<ThirdParty> getAll() {
        return thirdPartyService.getAll();
    }

    @GetMapping("/check/third-party/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ThirdParty getById(@PathVariable long id) {
        return thirdPartyService.getById(id);
    }

    @PostMapping("/create/third-party")
    @ResponseStatus(HttpStatus.CREATED)
    public ThirdParty create(@RequestBody @Valid ThirdPartyDTO thirdPartyDTO) {
        return thirdPartyService.createThirdParty(thirdPartyDTO);
    }

    @PatchMapping("/thirdPartyOperation")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void update (@RequestParam Integer hashedKey, @RequestBody AccountDTO accountDTO) {
        accountService.update(hashedKey, accountDTO);
    }
}
