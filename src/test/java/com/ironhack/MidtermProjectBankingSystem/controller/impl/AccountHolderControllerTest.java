package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.test.web.servlet.request.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;
import java.math.*;
import java.time.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;

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

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Pepe");
        accountHolder2.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder2.setUsername("pepe12345");
        accountHolder2.setPassword("$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW");
        accountHolder2.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder2.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder2);

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder2);
        roleRepository.save(role2);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1478")));
        account.setPrimaryOwner(accountHolder);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("246810")));
        account2.setPrimaryOwner(accountHolder);
        accountRepository.save(account2);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAccountsByAccountHolderId() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("pepa12345", "password"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1478"));
    }

    @Test
    void findAccountsById() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/" + accountRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("pepa12345", "password"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1478"));
    }

    @Test
    void transferMoney() throws Exception {

        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setAmount(new Money(new BigDecimal("30")));
        transactionDTO.setDescription("Gastos casa");
        transactionDTO.setOrigenAccountId(accountRepository.findAll().get(1).getId());
        transactionDTO.setDestinationAccountId(accountRepository.findAll().get(0).getId());
        transactionDTO.setNameOwnerDestinationAccount(accountRepository.findAll().get(0).getPrimaryOwner().getName());

        String body = objectMapper.writeValueAsString(transactionDTO);


        MvcResult result = mockMvc.perform(
                post("/transaction")
                        .with(SecurityMockMvcRequestPostProcessors
                                .httpBasic(accountRepository.findAll().get(1).getPrimaryOwner().getUsername(), "password"))
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Gastos casa"));
    }
}