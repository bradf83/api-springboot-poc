### Spring Boot API

* Uses the following
  * Spring Boot 2.1.4 (Java 11)
  * Spring Data Rest
  * Spring Data JPA
  * Spring Security
  * Okta Starter (1.1.0)
  * H2 In Memory Database
  * Lombok
  
This application is utilizing the most recent version of Spring Boot (2.1.4) and relies on Java 11.

### Start the Application

1. Checkout the code
1. Run the main Application class
1. You will require a client passing a proper bearer token to view the API
1. You can disable the security by commenting out Spring Security and OKTA in the POM file and then deleting or commenting out the SecurityConfig class.
1. Once you have done this the API should be available at Navigate to localhost:8080.

### Things to Note

1. Currently not doing anything for CORS as I have my UI Proxy to this address while developing.

### Implemented Items To check out

1. Specialized search queries, look in CompanyRepository.  Check out localhost:8080/companies/search for a listing of them.
1. JWT Resource server security with OKTA.  2 Properties in the application.properties and the SecurityConfig class. 

### Things To Do

1. Add testing
1. Can we implement something like TimeCop?  Possibly use the IClock Java interface to do something smart?
1. Can we serve all dates as UTC and run our server in UTC?  Think about JPA.
1. Upgrade to most recent OKTA library or maybe just implement JWT with Spring only??
1. Expose ID in REST data payload
1. Add role checks to API endpoints
1. Implement custom method instead of automagic REST methods.
1. Use Flyway for loading data.