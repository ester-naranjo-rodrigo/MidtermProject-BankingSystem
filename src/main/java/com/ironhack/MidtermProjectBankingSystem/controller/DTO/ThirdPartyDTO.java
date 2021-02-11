package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import javax.validation.constraints.*;

public class ThirdPartyDTO {

    @NotNull
    private String hashKey;

    @NotNull
    private String name;

    public ThirdPartyDTO() {
    }

    public ThirdPartyDTO(@NotNull String hashKey, @NotNull String name) {
        this.hashKey = hashKey;
        this.name = name;
    }

    public String getHashKey() {
        return hashKey;
    }

    public void setHashKey(String hashKey) {
        this.hashKey = hashKey;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
