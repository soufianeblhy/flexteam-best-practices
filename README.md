# 🎬 Flex IMDb (DRAFT !)

Flex IMDb is a project aimed at managing actors and films with CRUD operations, allowing users to establish relationships between them. This service is implemented in REST using Spring Boot and H2 database, following the hexagonal architecture.

## Features

- 💡 CRUD Operations
- 🔗 Relational Flexibility
- 🚀 RESTful Implementation
- 🗄️ H2 Database

## Technologies Used

- ⚙️ Spring Boot
- 🗃️ H2 Database
- ☕ Java 17

## Usage

To use Flex IMDb, clone the repository and run the application locally. Then, access the provided endpoints to perform CRUD operations on actors and films and establish relationships between them.

## Getting Started

1. 🔄 Clone the repository: `git clone <repository-url>`
2. 📂 Navigate to the project directory.
3. ▶️ Run the application using Maven or your preferred IDE.
4. 🌐 Access the provided endpoints to interact with the IMDb database.

TODO ADD SWAGGER URL
TODO REMOVE APPLICATION.YML and move to test resources

## Hexagonal Architecture

Flex IMDb is built following the hexagonal architecture, which promotes separation of concerns and modularity, enhancing maintainability and testability of the codebase.

## Best Practices

Flex IMDb incorporates all the best practices followed by our Flex Team, ensuring code quality, maintainability, and scalability.

## Libraries Used

### Spring Boot Dependencies:
- ⚙️ **spring-boot-starter**: Spring Boot starter for core functionality.
- ⚙️ **spring-boot-starter-data-jpa**: Starter for using Spring Data JPA with Hibernate.
- ⚙️ **spring-boot-starter-web**: Starter for building web, including RESTful, applications using Spring MVC.
- ⚙️ **spring-boot-starter-validation**: Starter for using Java Bean Validation with Hibernate Validator.
- ⚙️ **springdoc-openapi-starter-webmvc-ui**: Starter for OpenAPI 3 (Swagger) documentation with UI for Spring Web MVC.

### Database Library:
- 🗃️ **h2**: H2 Database Engine, a fast in-memory database.

### Mapping and Code Generation:
- 🛠️ **lombok**: Java library to minimize boilerplate code.
- 🛠️ **mapstruct**: Annotation processor for Java bean mapping.
- 🛠️ **lombok-mapstruct-binding**: Lombok support for MapStruct.

### Testing Libraries:
- 🧪 **spring-boot-starter-test**: Starter for testing Spring Boot applications with libraries including JUnit, Hamcrest, and Mockito.
- 🧪 **mockito-core**: Mockito framework for mocking objects during tests.

### OpenAPI Code Generation:
- 🛠️ **openapi-generator-maven-plugin**: Maven plugin for generating API clients, server stubs, and documentation from an OpenAPI 3 (Swagger) specification.

These libraries serve various purposes including dependency management, data persistence, web development, testing, code generation, and code coverage reporting, ensuring the project's robustness, maintainability, and scalability.

## Contributing

Contributions to Flex IMDb are welcome! Feel free to open an issue or submit a pull request.

---

Enjoy managing and exploring actor and film data with Flex IMDb!
