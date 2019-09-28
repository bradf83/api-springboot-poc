package com.example.sbapi.model.processor;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.rest.webmvc.support.RepositoryEntityLinks;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelProcessor;
import org.springframework.stereotype.Component;

/**
 * // Docs: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_the_resourceprocessor_interface
 * // Docs: https://docs.spring.io/spring-data/rest/docs/current/reference/html/#_programmatic_links
 */

@RequiredArgsConstructor
@Component
public class CompanyProcessor implements RepresentationModelProcessor<EntityModel<Company>> {
    private final RepositoryEntityLinks repositoryEntityLinks;

    /**
     * Adding an ownerLink to allow users of the API to select the currently selected owner.  Consider this, the system
     * has 3 owners and their links are "/owners/1", "/owners/2" and "/owners/3".  Before doing this when looking up
     * a Company you only received a link to owner in the following format "/company/:id/owner" which would bring you
     * back the owner but it would not allow you to select the current owner in a select box for example.  This allows
     * that.
     * @param companyResource the company resource.
     * @return the enhanced company resource.
     */
    @Override
    public EntityModel<Company> process(EntityModel<Company> companyResource) {
        companyResource.add(repositoryEntityLinks.linkToItemResource(OwnerRepository.class, companyResource.getContent().getOwner().getId()).withRel("ownerLink"));
        return companyResource;
    }
}
