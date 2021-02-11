package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.hibernate.annotations.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.security.crypto.bcrypt.*;
import org.springframework.security.crypto.password.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
@SpringBootTest
class AccountHolderControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Pepa");
        accountHolder.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder.setUsername("pepa12345");
        accountHolder.setPassword("$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW");
        accountHolder.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder);


        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser((User) accountHolder);
        roleRepository.save(role);

    }

    @AfterEach
    void tearDown() {
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
        roleRepository.deleteAll();
    }

    @Test
    void findAccountsByAccountHolderId() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts").with(user("pepa12345").password("$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Ciudad"));
    }

    @Test
    void create() {
    }
}