# spring-jwt-rest-api
Simple rest API with spring boot with JWT token Authentication.

## Setup
- in the src/main/resources/application.properties change the mogo uri to your URI. <br>
`spring.data.mongodb.uri=mongodb://your_host/your_db` 

## Usage
Response :
``` javascript
http://your_host/login

// Request Body 
{
 "user" : "yourusername",
 "password": "yourPassword"
}

{   
   "token":"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqY2Fuc2VjbyIsImlhdCI6MTU5NjY1Mzk4MCwiaXNzIjoidGF4aS1hcHAiLCJleHAiOjE1OTY3NDAzODB9.M_YSmrPkFjf2lAd4b5yDbat4B_QyvodCr55WKfJtrK0",
   "user":{
      "id":"5f28a18626979564c0e3a9d5",
      "names":"your names",
      "surnames":"your_surnames",
      "email":"your_mails@gmail.com",
      "username":"youtuser@",
      "profilePicture":"@pictureuti.com"
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
