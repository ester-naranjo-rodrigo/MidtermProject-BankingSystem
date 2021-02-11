package com.ironhack.MidtermProjectBankingSystem.model.Users;

import org.hibernate.annotations.*;

import javax.persistence.*;
import javax.persistence.Entity;
import javax.validation.constraints.*;
import java.util.*;

@Entity
@PrimaryKeyJoinColumn(name = "id")
@DynamicUpdate
public class Admin extends User{

    public Admin(String username, String password, Set<Role> roles, @NotNull String name) {
        super(username, password, roles, name);
    }
}
