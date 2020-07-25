# Lists the entries of the Accounting Journal

**URL** : `/accountingJournal/entries`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/entries`

**Method** : `GET`

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

