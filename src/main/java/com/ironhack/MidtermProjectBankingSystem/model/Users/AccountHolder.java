package com.ironhack.MidtermProjectBankingSystem.model.Users;

import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class AccountHolder extends User {

    @NotNull
    private Date dateOfBirth;

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

    @OneToMany(mappedBy = "primaryOwner")
    private List<Account> primaryAccounts;

    @OneToMany(mappedBy = "secondaryOwner")
    private List<Account> secondaryAccounts;
}
