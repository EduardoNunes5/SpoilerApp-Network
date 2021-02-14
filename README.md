# Spoiler App Network

## This API has been developed so users can freely share animes/series spoilers.

Used technologies:
* Spring Boot(Web, Data Jpa, Security, Validation)
* Jwt

In order to run this project you should have it cloned or downloaded,
once you have done it, you can either import it into any ide such as Eclipe or Intellij and run it,
or execute the following command in your terminal:
```maven 
mvn spring-boot:run
```

note that you should have maven installed on your computer, as well as java 8+ due to lambda expressions.

In this api, logged in users can perform crud operations in the following entites: spoiler/comment/replies,
while others who are logged out can only perform read operations.
