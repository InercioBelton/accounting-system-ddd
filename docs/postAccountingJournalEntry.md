# Posts into the accounting Journal

**URL** : `/accountingJournal/entry`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/entry`

**Method** : `POST`

**Data**

Query param `fromAccountNumber`: BigDecimal
Query param `toAccountNumber`: BigDecimal
Query param `description`: String
Query param `amount`: BigDecimal

## Success Response

**Code** : `200 OK`

 

**Example**

````json

{
    "code": 200,
    "message": "SUCCESS",
    "description": "Posted sucessfully into the accounting journal"
}

````

