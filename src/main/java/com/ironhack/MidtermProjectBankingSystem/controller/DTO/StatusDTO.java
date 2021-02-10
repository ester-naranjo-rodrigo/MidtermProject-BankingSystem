package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.enums.*;

import javax.validation.constraints.*;

public class StatusDTO {
    @NotNull
    private Status status;
    public StatusDTO(Status status) {
        this.status = status;
    }
    public StatusDTO() {
    }
    public Status getStatus() {
        return status;
    }
    public void setStatus(Status status) {
        this.status = status;
    }
}
