package com.ironhack.MidtermProjectBankingSystem.controller.impl;

import com.fasterxml.jackson.databind.*;
import com.ironhack.MidtermProjectBankingSystem.controller.DTO.*;
import com.ironhack.MidtermProjectBankingSystem.enums.*;
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
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers.springSecurity;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
class ThirdPartyControllerTest {

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
    private SavingsRepository savingsRepository;
    @Autowired
    private TransactionRepository transactionRepository;
    @Autowired
    private ThirdPartyRepository thirdPartyRepository;

    private MockMvc mockMvc;
    private ObjectMapper objectMapper = new ObjectMapper();


    @BeforeEach
    void setUp() {
        mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).apply(springSecurity()).build();

        AccountHolder accountHolder = new AccountHolder();
        accountHolder.setName("Elisa");
        accountHolder.setDateOfBirth(LocalDate.of(1994 , 11, 17));
        accountHolder.setUsername("elisa12345");
        accountHolder.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO/jlE7P6YO"); //password: 123456
        accountHolder.setPrimaryAddress(new Address("Calle Valencia", "Madrid", "España", 28123));
        accountHolderRepository.save(accountHolder);
        AccountHolder accountHolder2 = new AccountHolder();
        accountHolder2.setName("Juan");
        accountHolder2.setDateOfBirth(LocalDate.of(1985, 10, 28));
        accountHolder2.setUsername("juan12345");
        accountHolder2.setPassword("$2a$10$hr66If9xZyBdDWrSQeyLlORqrl7lSOaAOqKwb7ipcPoO"); //password: 123456
        accountHolder2.setPrimaryAddress(new Address("Calle Toledo", "Madrid", "Spain", 28569));
        accountHolderRepository.save(accountHolder2);
        Admin admin = new Admin();
        admin.setName("Luis");
        admin.setUsername("admin1");
        admin.setPassword("$2a$10$rZf8JHWZ1H0NXMKPFlNq1.Uj3WlOLmWygrTIov0dbKG7l4FAhVBey"); // password: 0000
        Role role = new Role();
        role.setName("ACCOUNTHOLDER");
        role.setUser(accountHolder);
        Role role2 = new Role();
        role2.setName("ACCOUNTHOLDER");
        role2.setUser(accountHolder);
        Role role3 = new Role();
        role3.setName("ADMIN");
        role3.setUser(admin);
        userRepository.saveAll(List.of(accountHolder, accountHolder2, admin));
        roleRepository.save(role2);
        roleRepository.save(role);
        roleRepository.save(role3);
        Account account = new Account();
        account.setBalance(new Money(new BigDecimal("5486")));
        account.setPrimaryOwner(accountHolder);
        accountRepository.save(account);
        Account account2 = new Account();
        account2.setBalance(new Money(new BigDecimal("10236")));
        account2.setPrimaryOwner(accountHolder);
        accountRepository.save(account2);
        Savings savings = new Savings();
        savings.setBalance(new Money(BigDecimal.valueOf(9874)));
        savings.setPrimaryOwner(accountHolder2);
        savings.setSecretKey("123456");
        savingsRepository.save(savings);
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName("Alimentacion Pepe");
        thirdParty.setHashedKey(313131);
        thirdPartyRepository.save(thirdParty);
    }

    @AfterEach
    void tearDown() {
        thirdPartyRepository.deleteAll();
        transactionRepository.deleteAll();
        accountRepository.deleteAll();
        roleRepository.deleteAll();
        accountHolderRepository.deleteAll();
        userRepository.deleteAll();
        savingsRepository.deleteAll();
    }

    @Test
    void getAll_basicAdminAuth_thirdPartiesList() throws Exception {
        ThirdParty thirdParty = new ThirdParty();
        thirdParty.setName("Inditex");
        thirdParty.setHashedKey(4444);
        thirdPartyRepository.save(thirdParty);
        MvcResult result = mockMvc.perform(get("/check/thirdParties").with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "0000"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Inditex"));
    }

    @Test
    void getById_basicAdminAuthAndId_thirdParty() throws Exception {
        MvcResult result = mockMvc.perform(get("/check/thirdParty/" + thirdPartyRepository.findAll().get(0).getId()).with(SecurityMockMvcRequestPostProcessors.
                httpBasic("admin1", "0000"))).andReturn();
        assertTrue(result.getResponse().getContentAsString().contains("Alimentacion Pepe"));
    }

    @Test
    void sendTransaction_hashKeyAndBodyOperationThirdPartyDTO_void() throws Exception {
        List<Savings> savings = savingsRepository.findAll();
        List<ThirdParty> thirdParties = thirdPartyRepository.findAll();
        OperationThirdPartyDTO operationThirdPartyDTO = new OperationThirdPartyDTO();
        operationThirdPartyDTO.setId(savings.get(0).getId());
        operationThirdPartyDTO.setTransactionType(TransactionType.SEND);
        operationThirdPartyDTO.setSecretKey("123456");
        operationThirdPartyDTO.setAmount(BigDecimal.valueOf(22));
        String body = objectMapper.writeValueAsString(operationThirdPartyDTO);
        MvcResult result = mockMvc.perform(patch("/thirdPartyOperation?hashedKey=" + thirdParties.get(0).getHashedKey())
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }

    @Test
    void receiveTransaction_hashKeyAndBodyOperationThirdPartyDTO_void() throws Exception {
        List<Savings> savings = savingsRepository.findAll();
        List<ThirdParty> thirdParties = thirdPartyRepository.findAll();
        OperationThirdPartyDTO operationThirdPartyDTO = new OperationThirdPartyDTO();
        operationThirdPartyDTO.setId(savings.get(0).getId());
        operationThirdPartyDTO.setTransactionType(TransactionType.RECEIVE);
        operationThirdPartyDTO.setSecretKey("123456");
        operationThirdPartyDTO.setAmount(BigDecimal.valueOf(22));
        String body = objectMapper.writeValueAsString(operationThirdPartyDTO);
        MvcResult result = mockMvc.perform(patch("/thirdPartyOperation?hashedKey=" + thirdParties.get(0).getHashedKey())
                .content(body).contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent())
                .andReturn();
    }
}