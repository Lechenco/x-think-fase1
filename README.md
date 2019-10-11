# X-think Fase 1 Repository


Spring Boot API to X-think internship selective process.

### H2 console

[localhost://8080/h2-console](localhost://8080/h2-console)

**Credentials**:

    DriverClass: org.h2.Driver
    JDBC URL: jdbc:h2:mem:xthinkSales
    User Name: sa
    Password: 


### Insert new Sale

    curl -X POST localhost:8080/insert -d "salesmanId=2&price=5.0"

### List Salesmen Performance Between Dates

    curl -X POST localhost:8080/list -d "start=2019-10-01&end=2019-10-31"
