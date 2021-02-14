# MidtermProject-BankingSystem


| Method | Route                                                           | Description                                             |
|--------|-----------------------------------------------------------------|---------------------------------------------------------|
| GET    | http://localhost:8080/accounts                                  | Return a list with all account holder's accounts        |
| GET    | http://localhost:8080/account/{id}                              | Return an account by Id                                 |
| GET    | http://localhost:8080/check/accountHolders                      | Return a list of all account holders                    |
| GET    | http://localhost:8080/check/accountHolder/{id}                  | Return an account holder by Id                          |
| GET    | http://localhost:8080/check/accounts                            | Return a list of all accounts                           |
| GET    | http://localhost:8080/check/account/{id}                        | Return an account by Id                                 |
| GET    | http://localhost:8080/check/allChecking                         | Return a list with all checking accounts                |
| GET    | http://localhost:8080/check/allStudentChecking                  | Return a list with all student checking accounts        |
| GET    | http://localhost:8080/check/allSavings                          | Return a list with all savings accounts                 |
| GET    | http://localhost:8080/check/allCreditCard                       | Return a list with all credit card accounts             |
| GET    | http://localhost:8080/check/thirdParties                        | Return a list with all third parties                    |
| GET    | http://localhost:8080/check/thirdParty/{id}                     | Return a third party by Id                              |
| POST   | http://localhost:8080/create/savings                            | Create a savings account                                |
| POST   | http://localhost:8080/create/creditcard                         | Create a credit card account                            |
| POST   | http://localhost:8080/create/checking                           | Create a checking account                               |
| POST   | http://localhost:8080/create/accountHolder                      | Create an account holder                                |
| POST   | http://localhost:8080/create/thirdParty                         | Create a third party                                    |
| POST   | http://localhost:8080/transaction                               | Create a transaction                                    |
| PATCH  | http://localhost:8080/update/changeStatus/{id}                  | Update the status of an account                         |
| PATCH  | http://localhost:8080/update/balance/{id}                       | Update the balance of an account                        |
| PATCH  | http://localhost:8080/thirdPartyOperation?hashedKey={hashedKey} | Make a third party operation and update balance account |
