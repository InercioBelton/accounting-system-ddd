# Finds an Accounting Journal Entry by description

**URL** : `/accountingJournal/entry/{description}`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/entry/Test - DEBIT`

**Method** : `POST`

**Data**

Path param `description`: String

## Success Response

**Code** : `200 OK`
 
**Example**

````json
{
    "entryId": 4,
    "description": "Test - DEBIT",
    "amount": -7000,
    "accountSide": "DEBIT",
    "creationDate": 1595541600000,
    "creationTime": 1595614917575,
    "account": {
        "id": 2,
        "accountNumber": 214322331,
        "accountName": "Test account 1"
    }
}

````

