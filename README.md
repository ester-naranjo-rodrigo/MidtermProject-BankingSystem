# MidtermProject-BankingSystem

## Requirements

- The system must have 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard

  - Checking Accounts should have:
  
    - A balance
    - A secretKey
    - A PrimaryOwner
    - An optional SecondaryOwner
    - A minimumBalance
    - A penaltyFee
    - A monthlyMaintenanceFee
    - A creationDate
    - A status (FROZEN, ACTIVE)
  
  - Student Checking Accounts are identical to Checking Accounts except that they do NOT have:
  
    - A monthlyMaintenanceFee
    - A minimumBalance
  
  - Savings are identical to Checking accounts except that they:
  
    - Do NOT have a monthlyMaintenanceFee
    - Do have an interestRate
  
  - CreditCard Accounts have:
  
    - A balance
    - A PrimaryOwner
    - An optional SecondaryOwner
    - A creditLimit
    - A interestRate
    - A penaltyFee

- The system must have 3 types of Users: Admins and AccountHolders

  - AccountHolders hould be able to access their own accounts and only their accounts when passing the correct credentials using Basic Auth. AccountHolders have:
  
    - A name
    - A date of birth
    - A primaryAddress (which should be a separate address class)
    - An optional mailingAddress
    
  - Admins only have a name.
  - ThirdParty Accounts have a hashed key and a name.

- Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts

  - Savings:
  
    - Savings accounts have a default interest rate of 0.0025
    - Savings accounts may be instantiated with an interest rate other than the default, with a maximum interest rate of 0.5
    - Savings accounts should have a default minimumBalance of 1000
    - Savings accounts may be instantiated with a minimum balance of less than 1000 but no lower than 100
  
  - CreditCards:
  
    - CreditCard accounts have a default creditLimit of 100
    - CreditCards may be instantiated with a creditLimit higher than 100 but not higher than 100000
    - CreditCards have a default interestRate of 0.2
    - CreditCards may be instantiated with an interestRate less than 0.2 but not lower than 0.1
  
  - CheckingAccounts:
  
    - When creating a new Checking account, if the primaryOwner is less than 24, a StudentChecking account should be created otherwise a regular Checking Account should be created.
    - Checking accounts should have a minimumBalance of 250 and a monthlyMaintenanceFee of 12

- Interest and Fees should be applied appropriately:
  
  - The penaltyFee for all accounts should be 40. If any account drops below the minimumBalance, the penaltyFee should be deducted from the balance automatically
  - Interest on savings accounts is added to the account annually at the rate of specified interestRate per year. When a savings Account balance is accessed, you must determine if it has been 1 year or more since the either the account was created or since interest was added to the account, and add the appropriate interest to the balance if necessary.
  - Interest on credit cards is added to the balance monthly. When the balance of a credit card is accessed, check to determine if it has been 1 month or more since the account was created or since interested was added, and if so, add the appropriate interest to the balance.

- Account Access:

  - Admins should be able to access the balance for any account and to modify it
  - AccountHolders should be able to access their own account balance
  -Account holders should be able to transfer money from any of their accounts to any other account (regardless of owner). The transfer should only be processed if the account has sufficient funds. The user must provide the Primary or Secondary owner name and the id of the account that should receive the transfer.
  - There must be a way for third party users to receive and send money to other accounts.
Third party users must be added to the database by an admin.
In order to receive and send money Third Party Users must provide their hashed key in the header of the HTTP request. They also must provide the amount, the Account id and the account secret key.

- Fraud Detection: the application must recognise patterns that indicate fraud and Freeze the account status when potential fraud is detected. Patterns that indicate fraud include:
  - Transactions made in 24 hours that total to more than 150% of the customers highest daily total transactions in any other 24 hour period.
  - More than 2 transactions occurring on a single account within a 1 second period.


## First steps

- Create two databases: [midterm](src/main/resources/static/midterm.sql) and [midterm_test](src/test/resources/static/midterm_test.sql)
- Run mvn spring-bot:run
- You can use this [examples](doc/postman.md) to run tests in postman

## API Routes

| Method | Route                                      | Description                                             |
|--------|--------------------------------------------|---------------------------------------------------------|
| GET    | /accounts                                  | Return a list with all account holder's accounts        |
| GET    | /account/{id}                              | Return an account by Id                                 |
| GET    | /check/accountHolders                      | Return a list of all account holders                    |
| GET    | /check/accountHolder/{id}                  | Return an account holder by Id                          |
| GET    | /check/accounts                            | Return a list of all accounts                           |
| GET    | /check/account/{id}                        | Return an account by Id                                 |
| GET    | /check/allChecking                         | Return a list with all checking accounts                |
| GET    | /check/allStudentChecking                  | Return a list with all student checking accounts        |
| GET    | /check/allSavings                          | Return a list with all savings accounts                 |
| GET    | /check/allCreditCard                       | Return a list with all credit card accounts             |
| GET    | /check/thirdParties                        | Return a list with all third parties                    |
| GET    | /check/thirdParty/{id}                     | Return a third party by Id                              |
| POST   | /create/savings                            | Create a savings account                                |
| POST   | /create/creditcard                         | Create a credit card account                            |
| POST   | /create/checking                           | Create a checking account                               |
| POST   | /create/accountHolder                      | Create an account holder                                |
| POST   | /create/thirdParty                         | Create a third party                                    |
| POST   | /transaction                               | Create a transaction                                    |
| PATCH  | /update/changeStatus/{id}                  | Update the status of an account                         |
| PATCH  | /update/balance/{id}                       | Update the balance of an account                        |
| PATCH  | /thirdPartyOperation?hashedKey={hashedKey} | Make a third party operation and update balance account |

## Tools

- Intellij 
- Spring Boot
- MySQL
- Postman
