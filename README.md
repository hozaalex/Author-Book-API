# Author-Book-API

##  Secure and Robust RESTful API for Author and Book Management

This repository contains a robust, production-ready **RESTful API** for managing authors and their corresponding literary works. Built with **Java** and the powerful **Spring Boot** framework, the API emphasizes security, data integrity, and reliability.

The service implements a full **JWT (JSON Web Token)** authentication flow using **Spring Security** to ensure all core data operations are securely protected.

##  Key Features

* **Security & Authentication (JWT)**:
    * Full **User Registration** and **Token Generation** (Login) endpoints.
    * **Stateless Authentication** using a custom **JWT Filter** to secure all endpoints.
    * **Role-Based Access Control** enforced via `@PreAuthorize` (e.g., requires `ADMIN` role for certain user management functions).
* **Data Persistence & Integrity**:
    * Uses **Spring Data JPA** for efficient database interaction.
    * Models a critical **One-to-Many (1-to-N)** relationship where one Author can have multiple Books.
    * **Bean Validation (JSR 380)** is used to enforce data quality and integrity on all incoming request payloads.
* **Core API Functionality**:
    * Full **CRUD** (Create, Read, Update, Delete) support for both Author and Book entities.
* **Development Practices**:
    * Comprehensive architectural structure (Controllers, Services, Repositories, Filters).
    * Includes **Integration Tests** to guarantee API reliability and correct data handling.

##  Technologies

| Technology | Purpose |
| :--- | :--- |
| **Java 17+** | Primary programming language. |
| **Spring Boot** | Core framework for building the production-ready RESTful service. |
| **Spring Security** | Handles authentication and authorization. |
| **JWT** | Used for secure, token-based authentication. |
| **Spring Data JPA** | Simplifies data access and manages the 1-to-N persistence layer. |
| **Maven** | Dependency management and build automation tool. |
| **Bean Validation** | Ensures input data meets quality and structural requirements. |
| **H2 Database** | Likely used as an in-memory database for testing and easy local development (or can be configured for PostgreSQL/MySQL). |

##  Getting Started

### Prerequisites

You will need the following installed on your local machine:

* [Java Development Kit (JDK) 17+](https://www.oracle.com/java/technologies/javase/jdk17-archive-downloads.html)
* [Apache Maven](https://maven.apache.org/download.cgi)

### Running the Application

1.  **Clone the repository:**

    ```bash
    git clone [https://github.com/hozaalex/Author-Book-API.git](https://github.com/hozaalex/Author-Book-API.git)
    cd Author-Book-API
    ```

2.  **Build the project** using Maven to download dependencies and compile the code:

    ```bash
    ./mvn clean install
    ```

3.  **Run the Spring Boot application:**

    ```bash
    ./mvn spring-boot:run
    ```

    The API will start running, typically available at `http://localhost:8080`.

## API Endpoints

### 1. Security Endpoints (Authentication & Authorization)

These endpoints are used for user management and acquiring the necessary access token.

| Resource | HTTP Method | Endpoint | Description | Access |
| :--- | :--- | :--- | :--- | :--- |
| **User** | `POST` | `/auth/addUser` | Creates a new user account (Registration). | Public |
| **User** | `POST` | `/auth/generateToken` | Authenticates a user and returns a **JWT** (Login). | Public |
| **User** | `DELETE` | `/auth/deleteUser/{id}` | Deletes a user by ID. | **ADMIN Role Required** |

### 2. Data Endpoints (Author and Book Management)

All data endpoints require a valid **JWT** in the `Authorization: Bearer <token>` header.

| Resource | HTTP Method | Endpoint | Description |
| :--- | :--- | :--- | :--- |
| **Author** | `POST` | `/authors` | Creates a new Author. |
| **Author** | `GET` | `/authors/{id}` | Retrieves a specific Author by ID. |
| **Author** | `GET` | `/authors` | Retrieves a list of all Authors. |
| **Author** | `PUT` | `/authors/{id}` | Updates an existing Author by ID. |
| **Author** | `DELETE` | `/authors/{id}` | Deletes an Author by ID. |
| **Book** | `POST` | `/books` | Creates a new Book, linking it to an existing Author. |
| **Book** | `GET` | `/books/{id}` | Retrieves a specific Book by ID. |
| **Book** | `GET` | `/books` | Retrieves a list of all Books. |
| **Book** | `PUT` | `/books/{id}` | Updates an existing Book by ID. |
| **Book** | `DELETE` | `/books/{id}` | Deletes a Book by ID. |

##  Testing

To execute the unit and integration tests packaged with the application:

```bash
./mvn test
```
