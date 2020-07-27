# Lists all Accounting Journal Entries that were created in a specific date

**URL** : `/accountingJournal/entries/creationDate`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/entries/creationDate`

**Method** : `GET`

**Data**

Path param `creationDate`: String (following the pattern 'yyyy/MM/dd', example: 2020/07/24)

## Success Response

**Code** : `200 OK`

 
**Example**

````json

[
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

