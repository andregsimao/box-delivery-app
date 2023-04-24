# box-delivery-app
The aim of this app is creating a console application that receives orders and a flight schedule as inputs 
and generate flight itineraries as output to deliver all orders in the flights scheduled.
<hr>

## Example  

### Loading a flight schedule:

- After running the application, the program will ask for the user input in the console:
```
-----------------------------------------------------------------

Choose and type the option number one of the following options:

0: Finish the program
1: Insert flight schedule
2: Check flight schedule
3: Generate itineraries

option: {{user input}}
```

- Typing 1 as the option 1 (Insert flight schedule), the application asks for the number of days:
```
number of days: {{user input}}
```

- Typing 2 as the number of days, the application will ask for the number of flights in each day and 
the departure and arrival airports of each flight in that day. So if the user input is 3 flights for each day:
```
number of flights in day 1: 3

Day 1, flight 1. Departure Airport Code: {{user input}}

Day 1, flight 1. Arrival Airport Code: {{user input}}

Day 1, flight 2. Departure Airport Code: {{user input}}

Day 1, flight 2. Arrival Airport Code: {{user input}}

Day 1, flight 3. Departure Airport Code: {{user input}}

Day 1, flight 3. Arrival Airport Code: {{user input}}

number of flights in day 2: 3

Day 2, flight 4. Departure Airport Code: {{user input}}

Day 2, flight 4. Arrival Airport Code: {{user input}}

Day 2, flight 5. Departure Airport Code: {{user input}}

Day 2, flight 5. Arrival Airport Code: {{user input}}

Day 2, flight 6. Departure Airport Code: {{user input}}

Day 2, flight 6. Arrival Airport Code: {{user input}}
```

- After inserting the flight schedule, it is possible to use option 2 to check the schedule added:
```
-----------------------------------------------------------------

Choose one of the following options:

0: Finish the program
1: Insert flight schedule
2: Check flight schedule
3: Generate itineraries

option: 2

-----------------------------------------------------------------
-----------------------------------------------------------------
Flight Schedule:

Flight: 1, departure: YUL, arrival: YYZ, day: 1
Flight: 2, departure: YUL, arrival: YYC, day: 1
Flight: 3, departure: YUL, arrival: YVR, day: 1
Flight: 4, departure: YUL, arrival: YYZ, day: 2
Flight: 5, departure: YUL, arrival: YYC, day: 2
Flight: 6, departure: YUL, arrival: YVR, day: 2

-----------------------------------------------------------------
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
java -jar target/delivery-box-app-1.0.0.jar
```

## run tests

```bash
mvn test
```
