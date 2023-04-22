# box-delivery-app
The aim of this app is receiving given orders and a flight schedule as inputs and generate flight itineraries as output 
to deliver all orders in the flights scheduled.
<hr>

* [JDK 20](https://www.oracle.com/java/technologies/javase/jdk20-archive-downloads.html)

## Example: 

### Flight Schedule Input:

```
Day 1:
Flight 1: Montreal airport (YUL) to Toronto (YYZ)
Flight 2: Montreal (YUL) to Calgary (YYC)
Flight 3: Montreal (YUL) to Vancouver (YVR)
Day 2:
Flight 4: Montreal airport (YUL) to Toronto (YYZ)
Flight 5: Montreal (YUL) to Calgary (YYC)
Flight 6: Montreal (YUL) to Vancouver (YVR)
```

### Orders Input File:

```
    file location: src/main/resources/coding-assigment-orders.json
    
    {
        "order-001": {
            "destination" : "YYZ"
        },
        "order-002": {
            "destination" : "YYZ"
        },
        ....
    }
```

### Output

# Implementation

# Build & Run

## run application

The application can be driven from the main method in the file Application.Java (package com.box.delivery.app)   

```bash
java src/main/java/com/box/delivery/app/Application.java
```

## build the project and package jar file into the target directory with maven 

```bash
mvn clean package
```

## run jar file created with maven

```bash
java -jar target/delivery-box-app-1.0.0-SNAPSHOT.jar
```

## run tests

```bash
mvn test
```
