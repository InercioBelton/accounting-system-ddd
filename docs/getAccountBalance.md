# Gets the current balance of an account

**URL** : `/accountingJournal/account/{accountNumber}/balance`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accountingJournal/account/1232132/balance`

**Method** : `POST`

**Data**

Path param `accountNumber`: BigDecimal

## Success Response

**Code** : `200 OK`
 
**Example**

````json
{
    "code": 200,
    "message": "SUCCESS",
    "description": "Current balance for account 123123222 is 3000.00"
}

````

