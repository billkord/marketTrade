# MarketTrade

## DEPENDENCIES
  * OpenJDK 1.8
  * MySQL (**schema**: *trade*, **user**: *root*, **password**: *root*). Only the schema creation is necessary. Tables will be generated (described below)
  * Node.js 6.x

## SERVER SIDE
  It contains two projects. Build all the projects using
  ``` bash
  mvn clean install
  ```
  while being at the project path */trade*

### 1. trade-initializer
  Initializes the database of the project with 2 users. It also get information about currency symbols and names from an API   provided by http://apilayer.net
  In order to generate the schema tables navigate to "*trade/trade-initializer/src/main/resources/application.properties*" and ensure that:
``` bash
spring.jpa.hibernate.ddl-auto=auto
```
  To run through command line navigate to "*trade/trade-initializer*" and run
  ``` bash
  mvn spring-boot:run
  ```

### 2. trade-webapp
  It is the main app. It consumes Json messages of type:
  ```javascript
    {
      "userId": some_number, 
      "currencyFrom": "some_string", 
      "currencyTo": "some_string", 
      "amountSell": some_number, 
      "amountBuy": some_number, 
      "rate": some_number, 
      "timePlaced" : "dd-MMM-yy hh:mm:ss", 
      "originatingCountry" : "some_string"
    }
```
Messages are sent via POST at http://localhost:8080/api/messages/consume.
<br /> Ensure that you have set credentials **"username"** to **oneperson@email.com** and **"password"** to **123456** in order to get permission for above url. Also using those credentials, you should use **userId**=**2** at the messages sent, to pass the validation stage.
  ``` bash
  curl -H "Content-Type: application/json" --request POST -d '{"userId": 2, "currencyFrom": "BBD", "currencyTo": "GBP", "amountSell": 1000, "amountBuy": 747.10, "rate": 0.7471, "timePlaced" : "24-JAN-15 10:27:44", "originatingCountry" : "GR"}' -u oneperson@email.com:123456 http://localhost:8080/api/messages/consume
  ```
<br/>To run through command line navigate to "*trade/trade-webapp*" and run
  ``` bash
  mvn spring-boot:run
  ```
  
## CLIENT SIDE
### trade-client

  Get all client dependencies by going to "*trade/trade-client*" and run
  ``` bash
    npm install
   ```
  <br/>To start the app and run (at the same path)
  ``` bash
  npm start dev
  ```
  After that open a browser and type http://localhost:9000. Ensure that you have send at least one request (one message) to server in order to see some information on the client.
