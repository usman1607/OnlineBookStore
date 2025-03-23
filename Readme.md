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
cd online-bookstore
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