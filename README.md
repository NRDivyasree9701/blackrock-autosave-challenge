# BlackRock Autosave Challenge

Spring Boot REST API implementation of the BlackRock Autosave coding challenge.

This project simulates an auto-investment system that:
- Parses transactions
- Validates transactions
- Applies temporal constraints
- Calculates investment returns (NPS & Index)
- Provides system performance metrics

---

## Tech Stack

- Java 17+
- Spring Boot
- Maven
- REST APIs
- Docker-ready

---

## How to Run

### Run locally

1. Clone the repository
2. Navigate to project folder
3. Run:

```
mvn spring-boot:run
```

Application runs on:

```
http://localhost:8080
```

---

## API Endpoints

---

### 1️⃣ Transaction Builder

POST  
`/blackrock/challenge/v1/transactions:parse`

Rounds transaction amount to next hundred and calculates remanent.

#### Sample Request
```json
[
  { "date": "2023-10-12 20:15:30", "amount": 250 }
]
```

#### Sample Response
```json
[
  {
    "date": "2023-10-12 20:15:30",
    "amount": 250,
    "ceiling": 300,
    "remanent": 50
  }
]
```

---

### 2️⃣ Transaction Validator

POST  
`/blackrock/challenge/v1/transactions:validator`

Validates transactions based on:
- Negative amounts
- Duplicate dates
- Wage investment limit

Returns:
- valid
- invalid
- duplicate

---

### 3️⃣ Temporal Constraints Filter

POST  
`/blackrock/challenge/v1/transactions:filter`

Applies q, p, and k period rules to determine valid transactions.

---

### 4️⃣ Returns Calculation

#### NPS
POST  
`/blackrock/challenge/v1/returns:nps`

#### Index Fund
POST  
`/blackrock/challenge/v1/returns:index`

Calculates:
- Total investment
- Profit using compound interest
- Tax benefit (NPS)
- Inflation-adjusted returns

---

### 5️⃣ Performance Report

GET  
`/blackrock/challenge/v1/performance`

Returns:

```json
{
  "time": "00:00:00.000",
  "memory": "33.41 MB",
  "threads": 16
}
```

Includes:
- Execution time
- Memory usage
- Active threads

---

## Project Structure

```
controller  → REST endpoints  
service     → Business logic  
model       → Request/Response models  
```

---

## Author

Developed as part of the BlackRock Coding Challenge.
