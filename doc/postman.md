# Postman Tests
The routes are based in tables created with src/main/resources/static/midterm.sql file.

## Get accounts of an account holder when is authenticated:
  - Method: GET
  - URL: http://localhost:8080/accounts
  - Username: username1
  - Password: password
  
## Get accounts by an account holder by account Id:
  - Method: GET
  - URL: http://localhost:8080/account/1
  - Username: username1
  - Password: password
 
## Get account holders by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/accountHolders
  - Username: admin1
  - Password: admin1
  
## Get account holders by an Admin by account holder Id:
  - Method: GET
  - URL: http://localhost:8080/check/accountHolder/1
  - Username: admin1
  - Password: admin1
  
## Get accounts by an admin:
  - Method: GET
  - URL: http://localhost:8080/check/accounts
  - Username: admin1
  - Password: admin1
  
## Get accounts by an Admin by account Id:
  - Method: GET
  - URL: http://localhost:8080/check/account/1
  - Username: admin1
  - Password: admin1
  
## Get all checking accounts by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/allChecking
  - Username: admin1
  - Password: admin1
  
## Get all student checking accounts by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/allStudentChecking
  - Username: admin1
  - Password: admin1
  
## Get all savings accounts by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/allSavings
  - Username: admin1
  - Password: admin1
  
## Get all credit card accounts by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/allCreditCard
  - Username: admin1
  - Password: admin1

## Get all third parties by an Admin:
  - Method: GET
  - URL: http://localhost:8080/check/thirdParties
  - Username: admin1
  - Password: admin1
  
## Get third party by an Admin by third party Id:
  - Method: GET
  - URL: http://localhost:8080/check/thirdParty/1
  - Username: admin1
  - Password: admin1
  
  
  
  
  
  
## Create savings account by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/savings
  - Username: admin1
  - Password: admin1
  - Body: 
    {
    "balance": 2000.00,
    "idPrimaryOwner": 1,
    "idSecondaryOwner": 2,
    "secretKey": "5678",
    "minimumBalance": 200.00,
    "interestRate": 0.01
    }

## Create credit card account by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/creditcard
  - Username: admin1
  - Password: admin1
  - Body:
  
## Create checking account by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/checking
  - Username: admin1
  - Password: admin1
  - Body:
  
## Create account holder by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/accountHolder
  - Username: admin1
  - Password: admin1
  - Body:  
  
## Create third party by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/thirdParty
  - Username: admin1
  - Password: admin1
  - Body:

## Create transaction by an Account Holder:
  - Method: POST
  - URL: http://localhost:8080/transaction
  - Username: username1
  - Password: password
  - Body:
  




