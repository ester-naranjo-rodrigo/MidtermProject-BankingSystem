# Postman Tests
The routes are based in tables created with [midterm.sql](https://github.com/ester-naranjo-rodrigo/MidtermProject-BankingSystem/blob/main/src/main/resources/static/midterm.sql) file.

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
    {
    "balance": 4000.00,
    "idPrimaryOwner": 1,
    "idSecondaryOwner": 2
     }
  
## Create checking account by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/checking
  - Username: admin1
  - Password: admin1
  - Body: 
    {
    "balance": 4000.00,
    "idPrimaryOwner": 1,
    "idSecondaryOwner": 2,
    "secretKey": 12345
    }
  
## Create account holder by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/accountHolder
  - Username: admin1
  - Password: admin1
  - Body: {
    "name": "Francisca",
    "dateOfBirth": "1963-06-13",
    "username": "paqui123",
    "password": "$2a$10$BE3p4yAl6JDeYhRuXmPUsOwFPn/2ZA8U7loYUoQ/.bk.OOgH10njW",
    "primaryAddress": {
        "street": "Calle Toledo",
        "city": "Madrid",
        "country": "España",
        "zipCode": 28123
    },
    "mailingAddress": {
        "street": "Calle Toledo",
        "city": "Madrid",
        "country": "España",
        "zipCode": 28123
    }
} 
  
## Create third party by an Admin:
  - Method: POST
  - URL: http://localhost:8080/create/thirdParty
  - Username: admin1
  - Password: admin1
  - Body: {
    "name": "Tienda alimentación"
}

## Create transaction by an Account Holder:
  - Method: POST
  - URL: http://localhost:8080/transaction
  - Username: username1
  - Password: password
  - Body: {
        "origenAccountId": 1,
        "destinationAccountId":2,
        "description": "Gastos casa",
        "amount": {
            "currency": "EUR",
            "amount": 66.00
        }, 
        "nameOwnerDestinationAccount":"Eduardo" 
}

## Update status account by an Admin:
  - Method: PATCH
  - URL: http://localhost:8080/update/changeStatus/1
  - Username: admin1
  - Password: admin1
  - Body: {
    "status": "FROZEN"
}

## Update balance account by an Admin:
  - Method: PATCH
  - URL: http://localhost:8080/update/balance/2
  - Username: admin1
  - Password: admin1
  - Body: {
    "balance": {
            "currency": "EUR",
            "amount": 1800.00
        }
}

## Third Party Operation:
  - Method: PATCH
  - URL: http://localhost:8080/thirdPartyOperation?hashedKey=14
  - Body: {
    "amount":200,
    "id":1,
    "secretKey":"12345",
    "transactionType": "RECEIVE"
}



