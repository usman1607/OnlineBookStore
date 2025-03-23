# Online Bookstore API - Spring Boot

## Overview

This is a Spring Boot-based REST API for an online bookstore. It allows users to search for books, add them to a cart, proceed to checkout using different payment methods, and view their purchase history.

## Features

*   Book Management: Search, filter, and retrieve books.

*   Cart Management: Add items to the cart, update quantities, and view the cart.

*   Checkout: Process payments using different methods.

*   Purchase History: View past purchases.

## Tech Stack

*   Backend: Java, Spring Boot, Spring Data JPA

*   Database: PostgreSQL

*   Testing: JUnit, Mockito

## Installation

### Prerequisites

*   JDK 17+

*   Maven 3+

*   PostgreSQL database

## API Endpoints

### Books

*   Get book by Id: Get /api/books/{id}

*   Get all books: GET /api/books

*   Search books: GET /api/books/search?title=xyz&author=abc&year=2023&genre=THRILLER

### Cart

*   Add to cart: POST /api/cart/add

*   View cart: GET /api/cart/{cartId}

### Checkout

*   Process checkout: POST /api/checkout/process

### Purchase History

*   View purchase history: GET /api/purchase-history/{userId}

## Setup

### Clone the repository:

```
git clone https://github.com/usman1607/OnlineBookStore.git
cd OnlineBookStore
```

### Configure database settings in application.properties:

*   Create your local database and input your spring.datasource.url, spring.datasource.username and spring.datasource.password in the application.properties file.

```
spring.datasource.url=jdbc:postgresql://localhost:5432/bookstore
spring.datasource.username=your_db_user
spring.datasource.password=your_db_password
```

### Build and run the project:

```
mvn clean install
mvn spring-boot:run
```

### Run the tests:

```
mvn test
```

## Testing the APIs

### Swagger UI is configured on the application

When you run the application, you can access the swagger ui on the default port: 8080 with the below url

```
http://localhost:8080/swagger-ui/index.html#/
```




# High-Level Design Overview

The Online Bookstore API follows a layered architecture to ensure scalability, maintainability, and modularity. The primary layers are:

*   Controller Layer (Handles API requests)

*   Service Layer (Business logic implementation)

*   Repository Layer (Data access using Spring Data JPA)

*   Database (PostgreSQL for storing data)

## Component Relationships

*   Users interact with the system via REST API.

*   BookController: Fetches book data from BookService, which retrieves information from BookRepository.

*   CartController: Manages user carts and interacts with CartService and CartRepository.

*   CheckoutController: Processes payments through different ICheckoutService implementations (Web, USSD, Transfer).

*   PurchaseHistoryController: Fetches past orders linked to a user.

## Scalability & Fault Tolerance

*   Load Balancing: The system can be horizontally scaled by running multiple instances behind a load balancer.

*   Database Optimization: Using indexing and optimized queries for fast retrieval.

*   Microservices Expansion: Checkout, Book Management, and Cart services can be separated into microservices in the future.


## Additional Notes:

*   User Entity is not included in the app, a user Id is just simulated to associate the cart and the checkout with a user.

*   The payment integration is not implemented, the payment is just simulated to be successful.

*   The database is  preload your database with 10 books inventory that run once when you run the application.