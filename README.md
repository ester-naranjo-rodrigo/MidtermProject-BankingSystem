# MidtermProject-BankingSystem

## Requirements

- The system must have 4 types of accounts: StudentChecking, Checking, Savings, and CreditCard

- The system must have 3 types of Users: Admins and AccountHolders

- Admins can create new accounts. When creating a new account they can create Checking, Savings, or CreditCard Accounts

- Interest and Fees should be applied appropriately

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
