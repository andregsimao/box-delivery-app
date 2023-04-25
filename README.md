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

### Viewing the flight schedule:

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
### Generating the flight schedule:

- After running the application and feeding the flights using the option 1 as described in the previous example,
the flights below are loaded with the input: 1 2 3 YUL YYZ YUL YYC YUL YVR 3 YUL YYZ YUL YYC YUL YVR.

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

- it is possible to use option 3 (Generate itineraries) from the main menu to generate the itinerary.
This option uses the file in the path *src/main/resources/coding-assigment-orders.json* to load the 
data in the following format.

- Orders Input File:

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

- The output of option 3 are the orders to be sent in each airplane scheduled considering that the 
orders listed in the json file are listed in priority order (1..N) and each flight can carry 20 orders.

```
Flight Schedule:

Flight: 1, departure: YUL, arrival: YYZ, day: 1
Flight: 2, departure: YUL, arrival: YYC, day: 1
Flight: 3, departure: YUL, arrival: YVR, day: 1
Flight: 4, departure: YUL, arrival: YYZ, day: 2
Flight: 5, departure: YUL, arrival: YYC, day: 2
Flight: 6, departure: YUL, arrival: YVR, day: 2

-----------------------------------------------------------------

Choose and type the option number one of the following options:

0: Finish the program
1: Insert flight schedule
2: Check flight schedule
3: Generate itineraries

option: 3

-----------------------------------------------------------------
-----------------------------------------------------------------
order: order-001, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-002, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-003, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-004, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-005, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-006, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-007, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-008, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-009, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-010, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-011, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-012, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-013, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-014, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-015, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-016, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-017, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-018, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-019, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-020, flightNumber: 1, departure: YUL, arrival: YYZ, day: 1
order: order-021, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-022, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-023, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-024, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-025, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-026, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-027, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-028, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-029, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-030, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-031, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-032, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-033, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-034, flightNumber: 4, departure: YUL, arrival: YYZ, day: 2
order: order-035, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-036, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-037, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-038, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-039, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-040, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-041, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-042, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-043, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-044, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-045, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-046, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-047, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-048, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-049, flightNumber: not scheduled
order: order-050, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-051, flightNumber: not scheduled
order: order-052, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-053, flightNumber: not scheduled
order: order-054, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-055, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-056, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-057, flightNumber: 2, departure: YUL, arrival: YYC, day: 1
order: order-060, flightNumber: 5, departure: YUL, arrival: YYC, day: 2
order: order-061, flightNumber: 5, departure: YUL, arrival: YYC, day: 2
order: order-062, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-063, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-064, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-065, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-066, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-067, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-068, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-069, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-070, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-071, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-072, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-073, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-074, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-075, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-076, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-077, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-078, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-080, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-081, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-082, flightNumber: 3, departure: YUL, arrival: YVR, day: 1
order: order-083, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-084, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-085, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-086, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-087, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-088, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-089, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-090, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-091, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-092, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-093, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-094, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-095, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-096, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-097, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-098, flightNumber: 6, departure: YUL, arrival: YVR, day: 2
order: order-099, flightNumber: 6, departure: YUL, arrival: YVR, day: 2

-----------------------------------------------------------------
```

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
java -jar target/delivery-box-app-2.0.0.jar
```

## run tests

```bash
mvn test
```
