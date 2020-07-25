# Lists all asset accounts

**URL** : `/accounts/asset`

**Example**: `http://127.0.0.1:8080/accounting-system-ddd/api/accounts/asset`

**Method** : `GET`

## Success Response

**Code** : `200 OK`
 
**Example**

````json

[
    {
        "id": 7,
        "accountNumber": 98798787,
        "accountName": "Test account",
        "currentBalance": 500
    },
    {
        "id": 2,
        "accountNumber": 4534543553,
        "accountName": "Test account",
        "currentBalance": 500
    }
]

````

