# Testing

## Date & Time
We should use `Instant` for our columns that store a point in time in the database.

```java
private Instant someDateOrTimeField
```
Now if we want to set that instant in a test we could do something like this:
```java
Clock fixedClock = Clock fixedClock = Clock.fixed(Instant.parse("2010-01-01T00:00:00.00Z"),ZoneOffset.UTC);
setSomeDateOrTimeField(Instant.now(fixedClock));
// OR
setSomeDateOrTimeField(fixedClock.instant());
```

## Model Layer

## Repository Layer
A few patterns for testing repositories.

* Speed up testing by using `@DataJpaTest` and `@AutoConfigureTestDatabase` instead of `@SpringBootTest`

```java
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
```
Read more about test slices here: [Spring Boot Slices](https://docs.spring.io/spring-boot/docs/current/reference/html/boot-features-testing.html#boot-features-testing-spring-boot-applications-testing-autoconfigured-jpa-test)

* Ensure that an entity is saved and read from the database (Option 1)
```java
@SpringBootTest
@Transactional 
...
@Autowired
EntityManager entityManager;

@Autowired
SomeJPARepository someRepository;

...
Entity someEntity;
// Do assertions
someEntity.setSomething();
this.someRepository.saveAndFlush(someEnttiy)
this.entity.Manager.refresh(someEntity)
// Do assertions
...
```
* Ensure that an entity is saved and read from the database (Option 2)
```java
@SpringBootTest
@Transactional
@AutoConfigureTestEntityManager 
...
@Autowired
TestEntityManager entityManager;

@Autowired
SomeJPARepository someRepository;

...
Entity someEntity;
// Do assertions
someEntity.setSomething();
this.entityManager.persistFlushFind(someEntity);
// Do assertions
...
```

## Controller Layer