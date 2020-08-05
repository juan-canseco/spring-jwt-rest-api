# spring-jwt-rest-api
Simple rest API with spring boot with JWT token Authentication.

## Setup
- in the src/main/resources/application.properties change the mogo uri to your URI. <br>
`spring.data.mongodb.uri=mongodb://your_host/your_db` 

## Usage
- http://localhost:8081/login 
Response :
``` javascript
{   
   "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqY2Fuc2VjbyIsImlhdCI6MTU5NjY1Mzk4MCwiaXNzIjoidGF4aS1hcHAiLCJleHAiOjE1OTY3NDAzODB9.M_YSmrPkFjf2lAd4b5yDbat4B_QyvodCr55WKfJtrK0",
   "user":{
      "id":"5f28a18626979564c0e3a9d5",
      "names":"Juan Pablo",
      "surnames":"Canseco Rios",
      "email":"jpcr.5m4r7@outlook.com",
      "username":"jcanseco",
      "profilePicture":"Juan"
   }
}
```

## Stack
- Spring Boot 3.2
- MongoDb
- jsonwebtokens 0.11.2

## Patterns
- MVC
- DTO
- Service

## License
[MIT](https://choosealicense.com/licenses/mit/)
