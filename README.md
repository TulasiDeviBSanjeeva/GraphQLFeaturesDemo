# What is GraphQL
It is a query language for making flexible API calls. One can avoid overfetchning of unwanted parts which is a big advantage over REST API. Just describe what you want in a single request with nested fields and be sure to receive a data payload of exact shape. 

## Launch application
This is a simple springboot application. Clone the repository and run using, `mvn spring-boot:run` command. 
Point your browser to the following url -

```
http://localhost:8081/graphiql
```

## Queries

Our queries are checked by server if they are valid against agreed contract(schema), and autowires corresponding resolvers to complete the request and prepares the data payload to be returned to the client. 

Fragments simply make your query clean, readable and reusable. You can wrap subfields into a Fragment and resuse them. (see Query 3)
You can as well define variables and default values . (see Query 4)

Mutation operation is used to change the serverside data.


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

* Query3 You can use aliases choose your desired different fields in each 

```
{
  customer1: customerById(id: 1) {  ## Aliases
    id
    name
    email
    customerPurchases {
      quantity
      status
      product {
        ...ProductInfo
      }
    }
  }
  customer2: customerById(id: 2) {
    name
    customerPurchases {
      quantity
      product {
        ...ProductInfo
      }
    }
  }
}

fragment ProductInfo on Product {   ## Fragments
  name
  realPrice
}

```
Response 

```
{
  "data": {
    "customer1": {    ##### Note customer1 datapayload
      "id": "1",
      "name": "Alia",
      "email": "alia@axe.com",
      "customerPurchases": [
        {
          "quantity": 10,
          "status": "OK",
          "product": {
            "name": "Brita tumbler",
            "realPrice": "Euros € : 25.0"
          }
        },
        {
          "quantity": 20,
          "status": "OK",
          "product": {
            "name": "Bony Jacke",
            "realPrice": "Euros € : 120.0"
          }
        }
      ]
    },
    "customer2": { ##### Note customer1 datapayload
      "name": "Bubbly",
      "customerPurchases": [
        {
          "quantity": 30,
          "product": {
            "name": "Bermuda Beauty",
            "realPrice": "Euros € : 60.0"
          }
        },
        {
          "quantity": 40,
          "product": {
            "name": "Cassendra Top",
            "realPrice": "Euros € : 80.0"
          }
        }
      ]
    }
  }
}

```

* Query4 

```
query Purchase($page: Int, $size: Int) {
  purchases(page: $page, size: $size) {
    id
    quantity
    status
    date
    customer {
      name
    }
    product {
      name
    }
  }
}

```

Query variables :

```
{
  "page": 1,
  "size": 2
}

```

Response :

```
{
  "data": {
    "purchases": [
      {
        "id": "2",
        "quantity": 20,
        "status": "OK",
        "date": "19/09/2020 07:13",
        "customer": {
          "name": "Alia"
        },
        "product": {
          "name": "Bony Jacke"
        }
      },
      {
        "id": "3",
        "quantity": 30,
        "status": "OK",
        "date": "19/09/2020 11:13",
        "customer": {
          "name": "Bubbly"
        },
        "product": {
          "name": "Bermuda Beauty"
        }
      }
    ]
  }
}

```

* Query5 : 

```
query {
  purchaseSummary{
    purchaseId
    customer
    product
    quantity
  }
}
```

Response : 

```
{
  "data": {
    "purchaseSummary": [
      {
        "purchaseId": "1",
        "customer": "Alia",
        "product": "Brita tumbler",
        "quantity": 10
      },
      {
        "purchaseId": "2",
        "customer": "Alia",
        "product": "Bony Jacke",
        "quantity": 20
      },
      {
        "purchaseId": "3",
        "customer": "Bubbly",
        "product": "Bermuda Beauty",
        "quantity": 30
      }
      ]
  }
}

```
* Mutation1 : 

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

* Mutation2 : 

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

* Mutation3 : 

```
mutation {
  deleteProduct(id: 6)
}
```

Response : 

```
{
  "data": {
    "deleteProduct": true
  }
}
```

### TODO :
1. Tests
2. Extend filtering capability and handle exceptions
3. Dockerize
4. OAuth grant



