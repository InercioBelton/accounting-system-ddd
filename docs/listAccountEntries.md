# Lists all posts made into an account

**URL** : `/accountingJournal/account/{accountNumber}/entries`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/1232132/entries`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

 
**Example**

````json
[
    {
        "entryId": 3,
        "description": "Test 5 - DEBIT",
        "amount": -500,
        "accountSide": "DEBIT",
        "creationDate": 1595790761523,
        "account": {
            "id": 2,
            "accountNumber": 76867687,
            "accountName": "Test account",
            "currentBalance": -600
        }
    },
    {
        "entryId": 4,
        "description": "Test 5 - CREDIT",
        "amount": 500,
        "accountSide": "CREDIT",
        "creationDate": 1595790761554,
        "account": {
            "id": 1,
            "accountNumber": 3243543454,
            "accountName": "Test account",
            "currentBalance": 200
        }
    },
    {
        "entryId": 5,
        "description": "Initial debit - DEBIT",
        "amount": -200,
        "accountSide": "DEBIT",
        "creationDate": 1595628000000,
        "account": {
            "id": 1,
            "accountNumber": 3243543454,
            "accountName": "Test account",
            "currentBalance": 200
        }
    },
    {
        "entryId": 7,
        "description": "Initial debit 1 - DEBIT",
        "amount": -100,
        "accountSide": "DEBIT",
        "creationDate": 1595692800000,
        "account": {
            "id": 1,
            "accountNumber": 3243543454,
            "accountName": "Test account",
            "currentBalance": 200
        }
    },
    {
        "entryId": 8,
        "description": "Test 6 - DEBIT",
        "amount": -100,
        "accountSide": "DEBIT",
        "creationDate": 1595628000000,
        "account": {
            "id": 2,
            "accountNumber": 76867687,
            "accountName": "Test account",
            "currentBalance": -600
        }
    },
    {
        "entryId": 9,
        "description": "Test 6 - CREDIT",
        "amount": 100,
        "accountSide": "CREDIT",
        "creationDate": 1595628000000,
        "account": {
            "id": 1,
            "accountNumber": 3243543454,
            "accountName": "Test account",
            "currentBalance": 200
        }
    },
    {
        "entryId": 11,
        "description": "Test 8 - DEBIT",
        "amount": -100,
        "accountSide": "DEBIT",
        "creationDate": 1595800800000,
        "account": {
            "id": 1,
            "accountNumber": 3243543454,
            "accountName": "Test account",
            "currentBalance": 200
        }
    },
    {
        "entryId": 12,
        "description": "Test 8 - CREDIT",
        "amount": 100,
        "accountSide": "CREDIT",
        "creationDate": 1595800800000,
        "account": {
            "id": 10,
            "accountNumber": 42352345435,
            "accountName": "Test account3",
            "currentBalance": 4000
        }
    },
    {
        "entryId": 13,
        "description": "Initial debit 13 - DEBIT",
        "amount": -100,
        "accountSide": "DEBIT",
        "creationDate": 1595865600000,
        "account": {
            "id": 10,
            "accountNumber": 42352345435,
            "accountName": "Test account3",
            "currentBalance": 4000
        }
    },
    {
        "entryId": 14,
        "description": "Initial credit - CREDIT",
        "amount": 4000,
        "accountSide": "CREDIT",
        "creationDate": 1595865600000,
        "account": {
            "id": 10,
            "accountNumber": 42352345435,
            "accountName": "Test account3",
            "currentBalance": 4000
        }
    }
]
````

