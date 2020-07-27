# Get the details of an Accounting Journal Entry

**URL** : `/accountingJournal/entry/{entryId}`

**Example** : `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/entry/4`

**Method** : `GET`

## Success Response

**Code** : `200 OK`

**Data**

Path param `entryId`: long

 
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

