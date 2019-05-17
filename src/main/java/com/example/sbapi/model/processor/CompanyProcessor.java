package com.example.sbapi.model.processor;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.ResourceProcessor;
import org.springframework.stereotype.Component;

/**
 * // Docs: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_the_resourceprocessor_interface
 * // Docs: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_programmatic_links
 */

@RequiredArgsConstructor
@Component
public class CompanyProcessor implements ResourceProcessor<Resource<Company>> {
    private final RepositoryEntityLinks repositoryEntityLinks;
    @Override
    public Resource<Company> process(Resource<Company> companyResource) {
        companyResource.add(repositoryEntityLinks.linkForSingleResource(OwnerRepository.class, companyResource.getContent().getOwner().getId()).withRel("owner-link"));
            return companyResource;

    }
}
