# About application
Application uses Spring Boot with Webflux, MongoDB (default installation on localhost without credentials, 
also embedded in memory version is used in tests).


### Building
`mvn clean package` - it will build entire application including ReactJs front-end. 

### Running
`java -jar target/demo-app-0.0.1-SNAPSHOT.jar`

### API Documentation

http://localhost:8080/swagger-ui/