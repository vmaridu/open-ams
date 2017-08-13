## How to consume OpenAMS REST API

 - OpenAMS REST API  provides Restful Web Services with JWT based Restful Session Management.

 - To get **JWT** , Client is required to pass **Authorization** in Basic Authentication format to **GET /auth** endpoint

 - If **Authorization** is ,
   - **VALID**             : then API responds with **JWT** in **Authorization** Response Header with 200 OK. User Information can be retrieved by parsing JWT Token. See : [How to read Data from JWT](./jwt.md) .

   - **INVALID or EMPTY**  : then the API will respond with 401 and Error Message


 -  Client is required to pass  **JWT** for any service calls (/api/ endpoints).

 - **JWT** should be passed in **Authorization** header. **JWT** value should be prefixed by *Bearer<SPACE>*

 - If **JWT** is INVALID , then the API will respond with 401 and Error Message

 - If User with valid Credentials is not authorized to access a requested resources , then API will respond with 403 Error Message.
