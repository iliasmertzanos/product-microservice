# Welcome!

Here is a few words about my application in order to get it easier for the user to understand how it works.

#Tech Stack

- Java 8 (or higher)
- Spring Boot
- Gradle
- JPA
- Empbedded H2 data base
- Swagger UI

# Code Description 

The microservice contains two end points to expose api data.
Please keep in mind that a Swagger UI is also available in order to be easier for the user to access the URLs directly.

Data are being automatically generated from spring boot using the scripts that are present in the file `/product-microservice/src/main/resources/data.sql`

Once the App is up an running one can access the endpoints over the following URLs:

* [Brand Controller](http://localhost:8080/v1/search/get/products)
* [Product Controller](http://localhost:8080/v2/search/get/product/2) (here we getting Product with id 2)
* [Swagger UI](http://localhost:8080/swagger-ui.html)



# Microservice structure

The microservice has two entities a Product and a Brand both related with each other over a `@OneToMany` relationship.
Every Brand bean has a list of allocated products. 

Having this structure we can be sure that we always get the products that belong to brand. We avoid getting for example products that do not belong to a brand.

## Architecture 

Moreover the project have multiple layers, which is quite beneficial in many ways like for testing, refactoring and maintaining.

* DAO Layer: Interface for the database using ``org.springframework.data.jpa.repository.JpaRepository<T, ID>`` to manipulate and persist Entities
* Data Transfer Objects: Objects which are used for outside communication via the API
* Controller Layer: Implements the processing logic of the web service, parsing of parameters and validation of in- and outputs.
* Service Layer: Implements the business logic and handles the access to the Data Access Objects.
* Entities : Functional Objects which are being persisted in the database.

## Unit Testing

In the folder ``/product-microservice/src/test/java/com/microservices/product/`` there are some unit testing classes based on Junit.
They go through some basic testing to check if the service and DAO layer are working properly.