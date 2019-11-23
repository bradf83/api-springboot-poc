package com.example.sbapi.repository;

import com.example.sbapi.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.time.Instant;
import java.util.List;

@RepositoryRestResource(collectionResourceRel = "companies", path = "companies")
public interface CompanyRepository extends JpaRepository<Company, Long>, QuerydslPredicateExecutor<Company> {
    List<Company> findByCodeStartsWith(@Param("startingLetters") String startingLetters);

    List<Company> findByCodeContainsOrNameContainsAllIgnoreCase(@Param("search") String searchCode, @Param("search") String searchName);

    // Method to test using Instant rather than Timestamp or LocalDateTime
    // Can pass parameter via Spring Data REST controller (....&time=2011-01-01T00:00:00Z)
    List<Company> findByCreatedAtBefore(Instant before);
}
