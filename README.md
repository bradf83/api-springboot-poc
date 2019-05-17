### Spring Boot API

* Uses the following
  * Spring Boot 2.1.5 (Java 11)
  * Spring Data Rest
  * Spring Data JPA
  * Spring Security
  * Spring Security Test (For Mocking Users)
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

1. Add more testing (minor testing started).  Split out testing to other classes.
1. Can we implement something like TimeCop?  Possibly use the IClock Java interface to do something smart?
1. Add role checks to API endpoints
1. Implement custom method instead of automagic REST methods.
1. Use Flyway for loading data.
1. Investigate validators further.  Can we reuse them for multiple events create/save for example?

### Data Model
This is a fake data model to try out some different features, relationships, others.  Lots of info straight from [Archer Wiki](https://archer.fandom.com)

1. Company (code, name, chargesGST, chargesPST, owner_id) *Implemented*
1. Owner (firstName, lastName) (Owns a company) *Implemented*
1. Employee (firstName, lastName, company_id, salary, title, position) *Implemented*
1. Product (name, price, comments, company_id) *Implemented*
1. Need to build a many to many relationship

### Dev Steps
This is a rough list of steps that allow a developer to create a resource on the api side (spring boot) and how to
use that resources on the ui side (react).

1. Build the model class (entity/document) along with the backing data store.  Ensure that it extends the common properties
1. Create the repository for the model.  Make sure to extend the appropriate repository base class.  Remember that all
methods (GET, POST, PATCH, DELETE) are exported by default and you need to turn them off if you want to disable them.  Check
the Spring Data Rest [documentation](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#repository-resources.collection-resource) for specific ways to do so
    * Remember you can now add custom methods to the repository for searching/paging for example.  Again check the spring
    data [documentation](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#repositories.query-methods.query-creation).
1. Create any validators for your model.  Ensure to register the validators or use the auto registration, find more info
in the [documentation](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#validation).
1. Create any resource processors for your model.  Maybe you need to add a link or some other property to your model.  A common use
case is adding a link to relationships as the HAL spec does not give relationship links that work for selection for example.
    * [Resource Processors](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_the_resourceprocessor_interface)
    * [Programmatic Links](https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_programmatic_links) 
1. You now have a resource that is exposed over the API, time to move to the front end.
1. Create a new react component to utilize your resource.  There are a few ways to do this so I am listing some helpful
links below.
    * [Main Docs](https://reactjs.org/docs/getting-started.html)
    * A [functional component](https://reactjs.org/docs/components-and-props.html#function-and-class-components)
        * [Hooks](https://reactjs.org/docs/hooks-intro.html)
    * A [class based component](https://reactjs.org/docs/components-and-props.html#function-and-class-components)
        * [Lifecycle methods](https://reactjs.org/docs/state-and-lifecycle.html#adding-lifecycle-methods-to-a-class)
    * [Fetch](https://developer.mozilla.org/en-US/docs/Web/API/Fetch_API) - Fetching data 
        * Alternatives
            * [Axios](https://github.com/axios/axios)
    * [React Router](https://reacttraining.com/react-router/web/guides/quick-start) - Navigtation 
1. I know the react portion does not have a great guide yet but it will come I am still learning and settling on a pattern.
In general:
    * Create a component
        * Does your component need to be behind security?  What roles can access and use it?  Are the views different for
        different roles?
    * Display or link to your component.