# Car Dealership Web Application

This repository contains the source code for a web application that serves as a car dealership platform. It allows customers to create an account, search for cars, add them to their cart, and make purchases. Additionally, the application provides management capabilities for the company to handle customer information, car inventory, and purchase records.

## Tech Stack

- Spring Boot
- Java 17
- Maven
- PostgreSQL
- H2 in-memory database
- MockMVC 
- JUnit 
- Mockito 
- Hibernate as ORM 

## Features

- User account creation: Customers can register and create their accounts.
- Car search: Customers can search for cars based on various criteria.
- Purchase functionality: Customers can complete the purchase of cars in their cart.
- Customer management: The company can manage customer information.
- Car management: The company can manage the car inventory.
- Purchase management: The company can view and manage customer purchases.

## Setup and Installation

1. Clone the repository:

   ```shell
   git clone <repository_url>
   
2. Install the required dependencies using Maven:


   mvn install
   
3. Configure the database:

    Make sure PostgreSQL is installed and running.
    Update the database connection properties in the application.properties file.

4. Run the application:


   mvn spring-boot:run
   
5. The application will be accessible at http://localhost:8080.

## Usage

    Create an account:
        Use the provided API to create a customer account.

    Search for cars:
        Utilize the search API and the the search functionality to find cars based on various criteria.

    Add cars to the purchase:
        Choose the desired cars and add them to the purchase.

    Manage customers, cars, and purchases:
        Use the provided APIs to manage customer information, car inventory, and purchase records.
        
 ## Tests  
 
 Unit tests: The application utilizes JUnit for unit tests and Mockito to mock dependencies.
 
 Integration tests: The application utilizes MockMVC and the H2 in-memory database for integration tests.
 
 ## Known Issues/Blokers
 
 I have encountered a blocker while implementing the APIs that take advantage of the many-to-many relationship between purchases and cars.
 
 ## Contributing
 
 Contributions to this project are welcome. If you find any bugs or have suggestions for improvements, please submit a pull request or open an issue.
   
   
