# webSorting - Web Application for Sorting Numbers

## Overview

webSorting is a web application developed using Jakarta EE and Thymeleaf for rendering HTML templates. It allows users to select from a variety of sorting algorithms to sort a list of numbers inputted through the web interface.

## Architecture

### High-Level Architecture

```plaintext
+-------------------+          +-------------------+
|   User Interface  |  <---->  |   Application     |
| (Web Browser/UI)  |          |   Server (Java)   |
+-------------------+          +-------------------+
```

### Component Diagram

```plaintext

+------------------+    +------------------+    +------------------+
|  StartingServlet |    | SortingServlet   |    |   LoggingService |
+---------+--------+    +--------+---------+    +---------+--------+
          |                      |                        |
          |                      |                        |
          v                      v                        v
+---------+--------+    +--------+---------+    +---------+--------+
| SortingService   |    | ConfigService    |    |  Gson (JSON)     |
+------------------+    +------------------+    +------------------+
```

## Components

### Servlets

1. **StartingServlet** - Handles initial requests to the application.
   - Path: `/api/home`
   - Responsibilities:
     - Initialize application components.
     - Provide links to other API endpoints.
2. **SortingServlet** - Processes sorting requests.
   - Path: `/api/sort`
   - Responsibilities:
     - Receive and parse user input.
     - Delegate sorting to the `SortingService`.
     - Return sorted results and relevant hyperlinks.

### Services

1. **SortingService** - A request-scoped bean that handles the sorting logic.
   - Responsibilities:
     - Choose the appropriate sorting algorithm based on the request.
     - Execute the sorting operation.
2. **ConfigService** - Manages application configuration and is available application-wide.
3. **LoggingService** - Provides logging functionality across the application.

### Models

1. **SortingAlgorithms** - Contains implementations of various sorting algorithms:
   - Quick Sort
   - Merge Sort
   - Heap Sort
   - Radix Sort
   - Bucket Sort

## Dependencies

- **Thymeleaf** - For rendering HTML views.
- **Jakarta Servlet API** - For handling HTTP requests and responses.
- **CDI (Contexts and Dependency Injection)** - For dependency injection.
- **Weld** - CDI implementation for managing bean lifecycle and dependencies.
- **GSON** - For converting Java objects to JSON format.

## Building and Running

### Prerequisites

- Java JDK 21 or higher
- Maven 3.6 or higher

### Build Instructions
`mvn clean install`

## Deployment

Deploy the generated WAR file to a Jakarta EE compatible server such as Tomcat or WildFly.

## API Endpoints

- **GET `/api/home`** - Landing page API, provides links to other endpoints.
- **POST `/api/sort`** - API to sort numbers. Accepts numbers and sorting algorithm type as input.

## Contributing

Contributions are welcome! Please fork the repository and submit a pull request with your changes.



