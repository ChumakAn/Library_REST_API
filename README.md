# Library_REST_API

## Description
In this project, CRUD API is implemented using *Spring Boot, Hibernate, JDBC, MySQL, Swagger UI, Maven*
This application is made via MVC pattern

### Features
+ Additional endpoints that extend functionality
+ Service layer with transfering entities to DTO
+ Controller with simple input validation
--- 
## Endpoints 
- create/update/delete book
- create/update/delete author
- show all books by author name
- show most selling book
- show most published book
- show list of most selling book by author name (using partial search by author name)
- show list of most published book by author name (using partial search by author name)
- show list of most successful book by author name (successBookRate = soldAmount/publishedAmount)
- show most successful author (successAuthorRate = sum of all successBootRate/number of books)

### Installation
1. Download the project
2. Run script with MySQL Workbench
3. Change credentials in `application.properties` file
4. Run project using your IDE
5. Open link http://localhost:8091/swagger-ui.html#/ in your browser to use Swagger UI
6. You're all set!!!
