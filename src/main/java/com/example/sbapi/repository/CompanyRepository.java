package com.example.sbapi.repository;

import com.example.sbapi.model.Company;
import com.example.sbapi.model.QCompany;
import com.querydsl.core.types.dsl.StringPath;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.querydsl.binding.QuerydslBinderCustomizer;
import org.springframework.data.querydsl.binding.QuerydslBindings;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.Instant;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long>,
        QuerydslPredicateExecutor<Company>, QuerydslBinderCustomizer<QCompany> {
    List<Company> findByCodeStartsWith(@Param("startingLetters") String startingLetters);

    List<Company> findByCodeContainsOrNameContainsAllIgnoreCase(@Param("search") String searchCode, @Param("search") String searchName);

    // Method to test using Instant rather than Timestamp or LocalDateTime
    // Can pass parameter via Spring Data REST controller (....&time=2011-01-01T00:00:00Z)
    List<Company> findByCreatedAtBefore(Instant before);

    /**
     * Additional Information
     *
     * It seems you can also provide an @QueryDslPredicate to the Predicate parameter and customize the binding there,
     * I was unable to get it to work in the repository.
     *
     * Links:
     * https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#core.web.type-safe
     * https://www.programcreek.com/java-api-examples/index.php?api=org.springframework.data.querydsl.binding.QuerydslPredicate
     * https://spring.io/blog/2015/09/04/what-s-new-in-spring-data-release-gosling#querydsl-web-support
     * https://github.com/spring-projects/spring-data-examples/blob/querydsl-binding/rest/starbucks/src/main/java/example/stores/StoreRepository.java
     * https://www.baeldung.com/rest-api-search-querydsl-web-in-spring-data-jpa
     * https://stackoverflow.com/questions/48378013/how-can-you-use-different-operators-with-querydsl-web?rq=1
     */

    /**
     * Customize the Query DSL binding so that all string properties are doing a like with ignore case
     * @param bindings
     * @param root
     */
    @Override
    default public void customize(QuerydslBindings bindings, QCompany root) {
        // All strings are doing a 'like' ignoring case
        bindings.bind(String.class).first(
                (StringPath path, String value) -> path.containsIgnoreCase(value));
    }
}
