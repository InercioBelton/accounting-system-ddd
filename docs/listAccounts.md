# Lists all accounts

**URL** : `/accountingJournal/accounts`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/accounts`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

 
**Example**

````json

[
    {
        "id": 1,
        "accountNumber": 1232132,
        "accountName": "Test account 2",
        "currentBalance": 500
    },
    {
        "id": 2,
        "accountNumber": 123123222,
        "accountName": "Test account",
        "currentBalance": 3000
    }
]

````

