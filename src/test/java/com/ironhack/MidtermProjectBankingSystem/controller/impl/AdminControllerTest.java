package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.fasterxml.jackson.core.*;
import com.fasterxml.jackson.databind.*;
import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.enums.*;
import com.ironhack.MidtermProjectBankingSystem.model.Accounts.*;
import com.ironhack.MidtermProjectBankingSystem.model.AuxClasses.*;
import com.ironhack.MidtermProjectBankingSystem.model.Users.*;
import com.ironhack.MidtermProjectBankingSystem.repository.Accounts.*;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class AdminControllerTest {

    @Autowired
    private WebApplicationContext webApplicationContext;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private AccountHolderRepository accountHolderRepository;
    @Autowired
    private AdminRepository adminRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private CheckingRepository checkingRepository;
    @Autowired
    private SavingsRepository savingsRepository;
    @Autowired
    private CreditCardRepository creditCardRepository;

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

        Admin admin = new Admin();
        admin.setName("Maria");
        admin.setUsername("admin1");
        admin.setPassword("$2a$10$NcfKcfldbLAEojIUdYgzSujzMgWH6hbCnw7y92FDgSsof/Mg/3MhW"); //admin1
        adminRepository.save(admin);

        userRepository.saveAll(List.of(accountHolder, accountHolder2, admin));

        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        roleRepository.save(role);

        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder2);
        roleRepository.save(role2);

        Role role3 = new Role();
        role3.setName("ADMIN");
        role3.setUser(admin);
        roleRepository.save(role3);

        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("1478")));
        account.setPrimaryOwner(accountHolder);
        accountRepository.save(account);

        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("246810")));
        account2.setPrimaryOwner(accountHolder);
        accountRepository.save(account2);

        Checking checking = new Checking();
        checking.setBalance((new Money(new BigDecimal("6200"))));
        checking.setPrimaryOwner(accountHolder);
        checking.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        checkingRepository.save(checking);

        CreditCard creditCard = new CreditCard();
        creditCard.setCreditLimit(new Money(new BigDecimal((300))));
        creditCard.setBalance(new Money(new BigDecimal((3400))));
        creditCard.setInterestRate(new BigDecimal((0.12)));
        creditCard.setPrimaryOwner(accountHolder);
        creditCardRepository.save(creditCard);

        Savings savings = new Savings();
        savings.setBalance(new Money(new BigDecimal((3400))));
        checking.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savings.setPrimaryOwner(accountHolder);
        savingsRepository.save(savings);

    }

    @AfterEach
    void tearDown() {
        checkingRepository.deleteAll();
        savingsRepository.deleteAll();
        creditCardRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
    }

    @Test
    void findAccountHolders() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/accountHolders").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "admin1"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepa"));
    }

    @Test
    void createAccountSavings() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Andrea");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("andreaa");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder4);

        SavingsDTO savingsDTO = new SavingsDTO();
        savingsDTO.setBalance(new BigDecimal((6400)));
        savingsDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe"); //secretKey
        savingsDTO.setIdPrimaryOwner(accountHolder4.getId());

        String body = objectMapper.writeValueAsString(savingsDTO);
        MvcResult result = mockMvc.perform(
                post("/create/savings")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("6400"));

    }

    @Test
    void createAccountCreditCard() throws Exception {

        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Andrea");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("andreaa");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder4);

        CreditCardDTO creditCardDTO = new CreditCardDTO();
        creditCardDTO.setCreditLimit(new BigDecimal((300)));
        creditCardDTO.setBalance(new BigDecimal((6400)));
        creditCardDTO.setInterestRate(new BigDecimal((0.19)));
        creditCardDTO.setIdPrimaryOwner(accountHolder4.getId());
        String body = objectMapper.writeValueAsString(creditCardDTO);
        MvcResult result = mockMvc.perform(
                post("/create/creditcard")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("0.19"));
    }

    @Test
    void createAccountChecking() throws Exception {
        AccountHolder accountHolder4 = new AccountHolder();
        accountHolder4.setName("Andrea");
        accountHolder4.setDateOfBirth(LocalDate.of(1980, 1, 8));
        accountHolder4.setUsername("andreaa");
        accountHolder4.setPassword("$2a$10$XZLkc4khf3SyiqtHeb1trekDiQxC17DWUX.J2Cx/tF/HdPqvL5Xoa"); //123
        accountHolder4.setPrimaryAddress(new Address("Calle Colon", "Cadiz", "Spain", 11100));
        accountHolderRepository.save(accountHolder4);

        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setSecretKey("$2a$10$WIAZju1Ca/uLJBUkeVPUpOm00DV3EQZC8rKnJ86FlQAAkJd0.SjZe");
        checkingDTO.setIdPrimaryOwner(accountHolder4.getId());
        checkingDTO.setBalance(new BigDecimal("1300"));
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result = mockMvc.perform(
                post("/create/checking")
                        .content(body)
                        .contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("1300"));

    }

    @Test
    void create_below24() throws Exception {
        AccountHolder accountHolder5 = new AccountHolder();
        accountHolder5.setName("Samuel");
        accountHolder5.setDateOfBirth(LocalDate.of(1997 , 10, 27));
        accountHolder5.setUsername("samucc");
        accountHolder5.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO/jlE7P6YO"); //password: 123456
        accountHolder5.setPrimaryAddress(new Address("Calle Mercedes", "Madrid", "España", 28019));
        accountHolderRepository.save(accountHolder5);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setIdPrimaryOwner(accountHolder5.getId());
        checkingDTO.setBalance(BigDecimal.valueOf(5000));
        checkingDTO.setSecretKey("12345asd");
        StudentChecking studentChecking = new StudentChecking();
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result =mockMvc.perform(
                post("/create/checking")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertFalse(result.getResponse().getContentAsString().contains("monthlyMaintenanceFee"));
        assertFalse(result.getResponse().getContentAsString().contains("minimumBalance"));
    }


    @Test
    void create_above24() throws Exception {
        AccountHolder accountHolder5 = new AccountHolder();
        accountHolder5.setName("Samuel");
        accountHolder5.setDateOfBirth(LocalDate.of(1993, 10, 27));
        accountHolder5.setUsername("samucc");
        accountHolder5.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO/jlE7P6YO"); //password: 123456
        accountHolder5.setPrimaryAddress(new Address("Calle Mercedes", "Madrid", "España", 28019));
        accountHolderRepository.save(accountHolder5);
        CheckingDTO checkingDTO = new CheckingDTO();
        checkingDTO.setIdPrimaryOwner(accountHolder5.getId());
        checkingDTO.setBalance(BigDecimal.valueOf(5000));
        checkingDTO.setSecretKey("12345asd");
        StudentChecking studentChecking = new StudentChecking();
        String body = objectMapper.writeValueAsString(checkingDTO);
        MvcResult result =mockMvc.perform(
                post("/create/checking")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("monthlyMaintenanceFee"));
        assertTrue(result.getResponse().getContentAsString().contains("minimumBalance"));
    }


    @Test
    void createAccountHolder() throws Exception {

        String json = "{ \"name\": \"Pepa\", \"dateOfBirth\": \"2010-06-13\", \"username\": \"k\", \"password\": " +
                "\"$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW\", \"primaryAddress\": {\"street\": \"aa\", " +
                "\"city\": \"aa\", \"country\": \"aa\", \"zipCode\": \"11\" },\"mailingAddress\": {\"street\": \"aa\",\"city\": " +
                "\"aa\",\"country\": \"aa\",\"zipCode\": \"11\" } }";

        MvcResult result =mockMvc.perform(
                post("/create/accountHolder")
                        .content(json).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Pepa"));
    }

    @Test
    void createThirdParty() throws Exception {
        ThirdPartyDTO thirdPartyDTO = new ThirdPartyDTO();
        thirdPartyDTO.setName("Inditex");

        String body = objectMapper.writeValueAsString(thirdPartyDTO);
        MvcResult result =mockMvc.perform(
                post("/create/thirdParty")
                        .content(body).contentType(MediaType.APPLICATION_JSON)
                        .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isCreated())
                .andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Inditex"));
    }

    @Test
    void updateStatus() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        StatusDTO statusDTO = new StatusDTO();
        statusDTO.setStatus(Status.FROZEN);
        String body = objectMapper.writeValueAsString(statusDTO);
        MvcResult result = mockMvc.perform(patch("/update/changeStatus/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void updateBalance() throws Exception {
        List<Account> accounts = accountRepository.findAll();
        BalanceDTO balanceDTO = new BalanceDTO();
        balanceDTO.setBalance(new Money(BigDecimal.valueOf(40000)));
        String body = objectMapper.writeValueAsString(balanceDTO);
        MvcResult result = mockMvc.perform(patch("/update/balance/"+ accounts.get(0).getId())
                .content(body).contentType(MediaType.APPLICATION_JSON)
                .with(SecurityMockMvcRequestPostProcessors.httpBasic("admin1", "admin1")))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}