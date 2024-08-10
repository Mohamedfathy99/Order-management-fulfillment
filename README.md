# Order Management Fulfillment

## Introduction
This repository contains a Spring Boot application designed to manage and fulfill orders. It simulates an order management system with basic functionalities to create and retrieve orders.

## Technologies
- **Java version**: `21`
- **Spring Boot version**: `3.3.2`
- **H2 In-Memory Database** for testing purposes
- **JUnit** for unit testing
- **Mockito** for mocking dependencies in tests
- **MapStruct** for mapping between objects
- **Swagger** for API documentation using Springfox
- **Postman** for testing, documenting, and sharing APIs

## Project Structure
The project follows a Domain-Driven Design (DDD) approach with the following package structure:
- `com.fathy.order_management_fulfillment`
    - `controller` - Contains REST controllers for handling API requests.
    - `entity` - Contains the entity classes for database tables.
    - `repo` - Contains repository interfaces for data access.
    - `service` - Contains service classes for business logic.

## API Endpoints

### Create Order
- **Endpoint**: `POST /api/make-order`
- **Description**: Creates a new order.
- **Request Body**: An `Order` object.
- **Response**: The created order.

### Get Order
- **Endpoint**: `GET /api/get-order`
- **Description**: Retrieves an order by ID.
- **Parameters**: `id` (Order ID)
- **Response**: The requested order.

## Configuration
- **Database Configuration**:
    - **H2 In-Memory Database** is used for testing. The configuration includes:
        - `spring.datasource.url=jdbc:h2:mem:orderdb`
        - `spring.datasource.driverClassName=org.h2.Driver`
        - `spring.datasource.username=sa`
        - `spring.datasource.password=password`
        - `spring.jpa.database-platform=org.hibernate.dialect.H2Dialect`
        - `spring.jpa.hibernate.ddl-auto=update`

## Testing
Unit tests are written using JUnit and Mockito to ensure the functionality of the application. Test cases cover the core components, including the controller, service, and repository layers.

## Getting Started

1. **Clone the Repository**:
   ```bash
   git clone https://github.com/Mohamedfathy99/order-management-fulfillment
   cd order-management-fulfillment

2. **Build the Project**:  
   ```bash
   ./mvnw clean install

3. **Run the Application Using Maven**:
   ```bash
   ./mvnw spring-boot:run
   
4. **Run the Application Using the JAR File**:
   ```bash
   java -jar target/order-management-fulfillment-0.0.1-SNAPSHOT.jar



