# GraphQLFeaturesDemo

## Launch application

```
http://localhost:8081/graphiql
```

## Test Results

* Query1 : 

```
{
  customers {
    id
    name
    email
    customerPurchases {
      id
      quantity
      status
      date
      product {
        id
        name
        price
        realPrice
      }
    }
  }
}
```

Response :

```
{
  "data": {
    "customers": [
      {
        "id": "1",
        "name": "Alia",
        "email": "alia@axe.com",
        "customerPurchases": [
          {
            "id": "1",
            "quantity": 10,
            "status": "OK",
            "date": "19/09/2020 07:13",
            "product": {
              "id": "1",
              "name": "Brita tumbler",
              "price": 25,
              "realPrice": "Euros € : 25.0"
            }
          },
          {
            "id": "2",
            "quantity": 20,
            "status": "OK",
            "date": "19/09/2020 07:13",
            "product": {
              "id": "2",
              "name": "Bony Jacke",
              "price": 120,
              "realPrice": "Euros € : 120.0"
            }
          }
        ]
      },
      {
        "id": "2",
        "name": "Bubbly",
        "email": "bubble@bae.com",
        "customerPurchases": [
          {
            "id": "3",
            "quantity": 30,
            "status": "OK",
            "date": "19/09/2020 11:13",
            "product": {
              "id": "3",
              "name": "Bermuda Beauty",
              "price": 60,
              "realPrice": "Euros € : 60.0"
            }
          },
          {
            "id": "4",
            "quantity": 40,
            "status": "OK",
            "date": "19/09/2020 11:13",
            "product": {
              "id": "4",
              "name": "Cassendra Top",
              "price": 80,
              "realPrice": "Euros € : 80.0"
            }
          }
        ]
      },
      {
        "id": "3",
        "name": "Charlie Cinta",
        "email": "cinta@cigna.com",
        "customerPurchases": [
          {
            "id": "5",
            "quantity": 50,
            "status": "OK",
            "date": "20/09/2020 15:13",
            "product": {
              "id": "5",
              "name": "Chameliah Shoes",
              "price": 130,
              "realPrice": "Euros € : 130.0"
            }
          }
        ]
      }
    ]
  }
}
```

* Query2 :

```
query {
  customerById(id: 2) {
    name
    customerPurchases {
      product {
        name
        realPrice
      }
      quantity
      date
    }
  }
}

```
Reponse : 

```
{
  "data": {
    "customerById": {
      "name": "Bubbly",
      "customerPurchases": [
        {
          "product": {
            "name": "Bermuda Beauty",
            "realPrice": "Euros € : 60.0"
          },
          "quantity": 30,
          "date": "19/09/2020 11:13"
        },
        {
          "product": {
            "name": "Cassendra Top",
            "realPrice": "Euros € : 80.0"
          },
          "quantity": 40,
          "date": "19/09/2020 11:13"
        }
      ]
    }
  }
}

```
* Mutation : 1

```
mutation {
  saveCustomer(customer: {id: 4, name: "Serria", email: "Serria@gmail.com"}) {
    id
    name
    email
  }
}
```

Response : 

```
{
  "data": {
    "saveCustomer": {
      "id": "4",
      "name": "Serria",
      "email": "Serria@gmail.com"
    }
  }
}

```

* Mutation : 2

```
mutation {
  savePurchase(purchase: {id: 6, quantity: 12, status: "OK", customerId: 3, productId: 6}) {
    id
    status
  }
}

```

Response : 

```
{
  "data": {
    "savePurchase": {
      "id": "6",
      "status": "OK"
    }
  }
}
```





