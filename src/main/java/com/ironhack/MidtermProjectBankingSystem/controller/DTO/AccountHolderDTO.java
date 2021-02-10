package com.ironhack.MidtermProjectBankingSystem.controller.DTO;

import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.*;
import java.time.*;
import java.util.*;

public class AccountHolderDTO {
    @NotNull
    private String name;

    @NotNull
    private LocalDate dateOfBirth;

    @NotNull(message = "Username can not be null")
    private String username;

    @NotNull(message = "Password can not be null")
    private String password;

    @Embedded
    private Address primaryAddress;

    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="street", column=@Column(name="mailing_street")),
            @AttributeOverride(name="city", column=@Column(name="mailing_city")),
            @AttributeOverride(name="country", column=@Column(name="mailing_country")),
            @AttributeOverride(name="zipCode", column=@Column(name="mailing_zip"))
    })
    private Address mailingAddress;

    public AccountHolderDTO() {
    }

    public AccountHolderDTO(@NotNull String name, @NotNull LocalDate dateOfBirth, @NotNull(message = "Username can not be null") String username, @NotNull(message = "Password can not be null") String password, Address primaryAddress, Address mailingAddress) {
        this.name = name;
        this.dateOfBirth = dateOfBirth;
        this.username = username;
        this.password = password;
        this.primaryAddress = primaryAddress;
        this.mailingAddress = mailingAddress;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public LocalDate getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(LocalDate dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public Address getPrimaryAddress() {
        return primaryAddress;
    }

    public void setPrimaryAddress(Address primaryAddress) {
        this.primaryAddress = primaryAddress;
    }

    public Address getMailingAddress() {
        return mailingAddress;
    }

    public void setMailingAddress(Address mailingAddress) {
        this.mailingAddress = mailingAddress;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
