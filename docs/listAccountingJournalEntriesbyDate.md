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
        "entryId": 3,
        "description": "Initial credit - CREDIT",
        "amount": 4000,
        "accountSide": "CREDIT",
        "creationDate": 1595541600000,
        "creationTime": 1595621601898,
        "account": {
            "id": 1,
            "accountNumber": 1232132,
            "accountName": "Test account 2",
            "currentBalance": 500
        }
    },
    {
        "entryId": 4,
        "description": "Initial debit - DEBIT",
        "amount": -500,
        "accountSide": "DEBIT",
        "creationDate": 1595541600000,
        "creationTime": 1595621660566,
        "account": {
            "id": 1,
            "accountNumber": 1232132,
            "accountName": "Test account 2",
            "currentBalance": 500
        }
    },
    {
        "entryId": 5,
        "description": "Test - DEBIT",
        "amount": -3000,
        "accountSide": "DEBIT",
        "creationDate": 1595541600000,
        "creationTime": 1595621720865,
        "account": {
            "id": 1,
            "accountNumber": 1232132,
            "accountName": "Test account 2",
            "currentBalance": 500
        }
    }
  ]

````

