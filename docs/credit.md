# Credit an account

**URL** : `/account/credit`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/account/credit`

**Method** : `POST`

**Data**

Query param `accountNumber`: BigDecimal

Query param `description`: String

Query param `amount`: BigDecimal

Query param `date`: String (following the pattern 'yyyy/MM/dd', example: 2020/07/24)

## Success Response

**Code** : `200 OK`

 

**Example**

````json

{
    "code": 200,
    "message": "SUCCESS",
    "description": "1000 was credited successfully into account 1232132"
}

````

