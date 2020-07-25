# Creates a new account

**URL** : `/account`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/account`

**Method** : `POST`

**Data**

Query param `accountNumber`: BigDecimal
Query param `accountName`: String

## Success Response

**Code** : `200 OK`

 

**Example**

````json

{
    "code": 200,
    "message": "SUCCESS",
    "description": "Account with number 123123222 was created successfully"
}

````

