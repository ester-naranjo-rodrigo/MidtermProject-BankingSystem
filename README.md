# MidtermProject-BankingSystem

## First steps

- Create two databases: [midterm](src/main/resources/static/midterm.sql) and [midterm_test](src/test/resources/static/midterm_test.sql)
- Run mvn spring-bot:run

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
