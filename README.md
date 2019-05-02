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
1. Ran into an interesting issue with generated links from API, if using a proxy the links were not being
rendered properly (not taking X-Forwarded-*).  This is due to a change in Spring that requires you to register
a bean for this behaviour.  The bean definition is below.  Check the following Spring issue for more information: [Issue](https://www.google.com/url?q=https://github.com/spring-projects/spring-hateoas/issues/862)

```java
@Bean
ForwardedHeaderFilter forwardedHeaderFilter() {
    return new ForwardedHeaderFilter();
}
```

### Implemented Items To check out

1. Specialized search queries, look in CompanyRepository.  Check out localhost:8080/companies/search for a listing of them.
1. JWT Resource server security with OKTA.  2 Properties in the application.properties and the SecurityConfig class. 

### Spring Data Rest Reminders

* Can use projections for loading less data or more data (for example relationships)
* Can use version/modified data to reduce data requests and ensure you don't overwrite others changes
* Validations can be implemented in multiple ways, handlers, events, look at the docs
* HAL Browser can be used for quick testing
* Can export or disable some actions 
* Can add additional links to resources
* Can create custom controller methods, look at the docs

### Spring Data Rest Questions

* Can you use different projections depending on roles?

### Things To Do

1. Add testing
1. Can we implement something like TimeCop?  Possibly use the IClock Java interface to do something smart?
1. Add role checks to API endpoints
1. Implement custom method instead of automagic REST methods.
1. Use Flyway for loading data.

### Data Model
This is a fake data model to try out some different features, relationships, others.  Lots of info straight from [Archer Wiki](https://archer.fandom.com)

1. Company (code, name, chargesGST, chargesPST, owner_id) *Implemented*
1. Owner (firstName, lastName) (Owns a company) *Implemented*
1. Employee (firstName, lastName, company_id, salary, title, position) *Implemented*
1. Product (name, price, comments, company_id) *Implemented*
1. Need to build a many to many relationship