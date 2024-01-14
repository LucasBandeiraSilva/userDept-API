# API PRODUCTS
Api RestFUl with Spring boot (version 3.2.1) for Users with a department, you can create, delete, update and of course list the users

## Use requirements
``Java version 17`` <br>
``Postman or insomnia to test the requests``

## Used dependencies
- ``Spring Boot``<br>
- ``Lombok``<br>
- ``Spring data JPA``<br>
- ``H2``<br>
- ``Spring security``<br>
- ``Spring validation``<br>



## How to run the project?
To start using this application, you need to have Java installed on your machine. After that, just go in a java class (Visual Studio code) and run java. If you are using Intelij just look for the main class and Execute the code clicking int the green play button

# HOW TO ACCESS THE H2 DATABASE
- ``http://localhost:8080/h2-console ``
> See the file: `` aplication.properties`` for more info


# RESTful resource routing for User

- ## Get One User
``Method : GET`` <br>
``http://localhost:8080/users/{id}``
- ## Get All Users
``Method : GET`` <br>
``http://localhost:8080/users``
- ## Create a new user
``Method : POST`` <br>
``http://localhost:8080/users`` <br>
- ## Update an user
``Method : PUT`` <br>
``http://localhost:8080/users{id}`` <br>
- ## Delete an user
``Method : DELETE`` <br>
``http://localhost:8080/users/{id}``

# 
## Warning

you must replace {id} for the real id when you test the route


# creating a user

```
{
    "name":"Lucas",
    "age":20,
    "email":"lucas@gmail.com",
    "password": "lucas123",
    "department":{
        "id":1
    }
}
```