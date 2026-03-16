# ☁️ WeatherApp - Spring Boot Backend

[![Spring Boot](https://img.shields.io/badge/Spring--Boot-4.0.3-brightgreen.svg)](https://spring.io/projects/spring-boot)
[![Java](https://img.shields.io/badge/Java-17-orange.svg)](https://www.oracle.com/java/)
[![MongoDB](https://img.shields.io/badge/MongoDB-Latest-green.svg)](https://www.mongodb.com/)
[![License](https://img.shields.io/badge/License-MIT-blue.svg)](LICENSE)

A robust, enterprise-grade backend service for the WeatherApp, built with Spring Boot and MongoDB. This microservice provides reliable weather data management, real-time updates, and is architected for scalability and future intelligence.

---

## 🚀 Key Features

- **RESTful API Architecture:** Seamlessly integrates with the React frontend.
- **Real-time Data Fetching:** Optimized weather data retrieval via third-party APIs.
- **Persistent Storage:** MongoDB integration for caching and historical data.
- **Health Monitoring:** Integrated Spring Boot Actuator for system health and metrics.
- **Validation:** Strong data validation using Jakarta Validation.
- **Websocket Support:** Foundations for real-time weather alerts.

---

## 🛠 Tech Stack

- **Core:** Java 17, Spring Boot 4.0.3
- **Data Layer:** Spring Data MongoDB
- **Utilities:** Lombok (Clean Code), Spring Boot Actuator (Monitoring)
- **Testing:** JUnit 5, Mockito, Spring Boot Test
- **Build Tool:** Maven

---

## 📂 Project Structure

```text
weather
├── src/main/java/com/example/weather
│   ├── controller/      # API Endpoints
│   ├── service/         # Business Logic
│   ├── repository/      # MongoDB Access
│   ├── model/           # Data Entities
│   └── exception/       # Global Error Handling
└── src/main/resources
    └── application.properties
```

---

## ⚙️ Setup & Installation

1. **Clone the repository:**
   ```bash
   git clone <repo-url>
   cd WeatherApp-SpringBoot-/weather
   ```

2. **Configure MongoDB:**
   Update `src/main/resources/application.properties` with your MongoDB URI.

3. **Run the application:**
   ```bash
   ./mvnw spring-boot:run
   ```

---

## 🧪 Microservice Testing

This project prioritizes quality through a comprehensive testing suite:

- **Unit Testing:** Using JUnit 5 and Mockito to test services in isolation.
- **Integration Testing:** Spring Boot Test for validating database interactions and API flows.
- **Actuator Health Checks:** Monitor system status via `/actuator/health`.

Run tests:
```bash
./mvnw test
```

---

## 🔮 Future Roadmap

### 🤖 Weather Prediction (AI/ML)
We are integrating a predictive analytics engine to:
- Forecast weather patterns based on historical data.
- Utilize Machine Learning models for high-accuracy local predictions.
- Provide personalized weather recommendations.

### ♾️ DevOps & CI/CD
Future integration with industry-standard DevOps tools:
- **Docker:** Containerization for consistent environments.
- **Kubernetes:** Orchestration for scaling microservices.
- **Jenkins/GitHub Actions:** Automated CI/CD pipelines.
- **Prometheus & Grafana:** Advanced monitoring and visualization.

---

## 📝 API Endpoints (Preview)

- `GET /api/weather` - Fetch current weather data.
- `GET /api/history` - Retrieve historical weather records.

---

Developed by [Vimukthi Buddika](https://github.com/BOBBY01151).