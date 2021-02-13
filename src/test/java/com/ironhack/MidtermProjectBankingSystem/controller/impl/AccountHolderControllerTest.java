package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.enums.Status;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.Address;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Transaction.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Users.*;
import org.apache.tomcat.jni.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.security.test.web.servlet.request.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.setup.*;
import org.springframework.web.context.*;

import java.lang.*;
import java.lang.Thread;
import java.math.*;
import java.time.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
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
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private SavingsRepository savingsRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();

    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Pepa");
        accountHolder.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder.setUsername("pepa12345");
        accountHolder.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        accountHolder.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder);

        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Pepe");
        accountHolder2.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder2.setUsername("pepe12345");
        accountHolder2.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        accountHolder2.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder2.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder2);

        AccountHolder accountHolder3 = new AccountHolder();
        accountHolder3.setName("Ricardo");
        accountHolder3.setDateOfBirth(LocalDate.of(2020, 1, 8));
        accountHolder3.setUsername("ricardo");
        accountHolder3.setPassword("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        accountHolder3.setPrimaryAddress(new Address("Calle bla bla", "Ciudad", "País ...", 1234));
        accountHolder3.setMailingAddress(new Address("Avenida bla bla", "Ciudad ..", "País ...", 5678));
        accountHolderRepository.save(accountHolder3);

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder2);
        roleRepository.save(role2);

        Role role3 = new Role();
        role3.setName("ACCOUNTHOLDER");
        role3.setUser(accountHolder3);
        roleRepository.save(role3);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1478")));
        account.setPrimaryOwner(accountHolder3);
        account.setDateOfCreation(LocalDate.of(2015,12,20));
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("246810")));
        account2.setPrimaryOwner(accountHolder3);
        account.setDateOfCreation(LocalDate.of(2018,12,20));
        accountRepository.save(account2);

        Savings savings = new Savings();
        savings.setBalance(new Money(new BigDecimal((4800))));
        savings.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings.setPrimaryOwner(accountHolder);
        savings.setStatus(Status.ACTIVE);
        savings.setMinimumBalance(new Money(BigDecimal.valueOf(100)));
        savings.setDateOfCreation(LocalDate.of(2018,12,20));
        savings.setDateOfLastAccess(LocalDate.of(2018,12,20));
        savingsRepository.save(savings);
        accountRepository.save(savings);

        Savings savings2 = new Savings();
        savings2.setBalance(new Money(new BigDecimal((8400))));
        savings2.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings2.setPrimaryOwner(accountHolder2);
        savings2.setStatus(Status.ACTIVE);
        savings2.setMinimumBalance(new Money(BigDecimal.valueOf(100)));
        savings2.setDateOfCreation(LocalDate.of(2018,12,20));
        savings.setDateOfLastAccess(LocalDate.of(2018,12,20));
        savingsRepository.save(savings2);
        accountRepository.save(savings2);
    }

    @AfterEach
    void tearDown() {
        transactionRepository.deleteAll();
        savingsRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAccountsByAccountHolder() throws Exception {
        MvcResult result = mockMvc.perform(get("/accounts").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("ricardo", "secretKey"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("246810"));
    }

    @Test
    void findAccountsById() throws Exception {
        MvcResult result = mockMvc.perform(get("/account/" + accountRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("ricardo", "secretKey"))).andReturn();
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
                                .httpBasic(accountRepository.findAll().get(1).getPrimaryOwner().getUsername(), "secretKey"))
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
        )
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Gastos casa"));
    }




    @Test
    void create_fraudChecker() throws Exception {
        List<Savings> savingss = savingsRepository.findAll();
        Savings originAccount = savingss.get(0);
        Savings destinationAccount = savingss.get(1);
        TransactionDTO transactionDTO = new TransactionDTO();
        transactionDTO.setOrigenAccountId(originAccount.getId());
        transactionDTO.setDestinationAccountId(destinationAccount.getId());
        transactionDTO.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO.setDescription("Blablabla");
        transactionDTO.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO.setTransactionDate(new Date());
        String body = objectMapper.writeValueAsString(transactionDTO);
        MvcResult result =mockMvc.perform(
                post("/transaction")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isCreated())
                .andReturn();
        Thread.sleep(500);
        TransactionDTO transactionDTO2 = new TransactionDTO();
        transactionDTO2.setOrigenAccountId(originAccount.getId());
        transactionDTO2.setDestinationAccountId(destinationAccount.getId());
        transactionDTO2.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO2.setDescription("Blablabla");
        transactionDTO2.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO2.setTransactionDate(new Date());
        String body2 = objectMapper.writeValueAsString(transactionDTO2);
        MvcResult result2 =mockMvc.perform(
                post("/transaction")
                        .content(body2).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isCreated())
                .andReturn();
        Thread.sleep(500);
        TransactionDTO transactionDTO3 = new TransactionDTO();
        transactionDTO3.setOrigenAccountId(originAccount.getId());
        transactionDTO3.setDestinationAccountId(destinationAccount.getId());
        transactionDTO3.setAmount(new Money(BigDecimal.valueOf(500)));
        transactionDTO3.setDescription("Blablabla");
        transactionDTO3.setNameOwnerDestinationAccount(destinationAccount.getPrimaryOwner().getName());
        transactionDTO3.setTransactionDate(new Date());
        String body3 = objectMapper.writeValueAsString(transactionDTO3);
        MvcResult result3 =mockMvc.perform(
                post("/transaction")
                        .content(body3).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic(originAccount.getPrimaryOwner().getUsername(), "secretKey")))
                .andExpect(status().isForbidden())
                .andReturn();
    }

}