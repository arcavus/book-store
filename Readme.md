##ReadingIsGood online books Automation Project
ReadingIsGood is an online books retail firm which operates only on the Internet. Main target of ReadingIsGood is to
deliver books from its one centralized warehouse to their customers within the same day. That is why stock consistency
is the first priority for their vision operations.

## FEATURES
    • Registering New Customer 
    • Placing a new order
    • Tracking the stock of books
    • List all orders of the customer with Pagination
    • Viewing the order details
    • Query Monthly Statistics  

## TOOLS
- Java 11
- Spring Boot 2.5.2
    - Spring Security
    - Bearer Token Auth (JWT)
    - Spring Data MongoDB
- Lombok
- MongoDB
- Swagger
- Docker
- Docker Compose
- Redis(I think stock count race condition problem solved via Redis Lock Mechanism But I am not sure.
Couchbase technology maybe more proper solution to race condition problem concurrent thread architecture. )

## COMPILE AND RUN

**Compile**

Requires **JDK11**, **Maven** and **Docker**.

Before building the application especially for the integration tests, mongodb should be running first,
That's why **in the beginning** you should run this command and run mongodb containers:

    > docker-compose up --build -d

## USAGE

After compile & run the application successfully it can be tested from Postman.

ReadingIsGood **Postman** Collection can be found under this directory:

    /postman/Book Store.postman_collection.json


**Swagger** can be accessed from:

http://localhost:9090/swagger-ui.html


![img.png](img.png)
