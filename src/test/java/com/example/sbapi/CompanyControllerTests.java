package com.example.sbapi;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTests {

    @Autowired
    private MockMvc mvc;
    @Autowired
    private CompanyRepository companyRepository;

    @Test
    @WithMockUser
    public void whenListingCompanies() throws Exception {
        mvc.perform(get("/companies")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(4)));
    }

    @Test
    @WithMockUser
    public void whenFilteringCompanies() throws Exception {
        mvc.perform(get("/companies/search/findByCodeContainsOrNameContainsAllIgnoreCase").param("search", "fa")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(1)));

        mvc.perform(get("/companies/search/findByCodeContainsOrNameContainsAllIgnoreCase").param("search", "FA")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(1)));

        mvc.perform(get("/companies/search/findByCodeContainsOrNameContainsAllIgnoreCase").param("search", "")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(4)));

        mvc.perform(get("/companies/search/findByCodeContainsOrNameContainsAllIgnoreCase").param("search", "abcd")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(1)));
    }

    @Test
    @WithMockUser
    public void whenLookingUpASingleCompanyTheOwnerLinkIsPresent() throws Exception {
        // Get the first company, look it up and then validate it has it's ownerLink and its the link that's expected.
        Company company = companyRepository.findAll().iterator().next();
        mvc.perform(get("/companies/" + company.getId())
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._links.ownerLink.*", hasSize(1)))
                .andExpect(jsonPath("$._links.ownerLink.href", is("http://localhost/owners/" + company.getOwner().getId())))
                .andDo(print());

    }

    @Test
    @WithMockUser
    public void whenFilteringCompaniesByInstant() throws Exception {

        //Clock fixedClock = Clock.fixed(Instant.parse("2010-01-01T00:00:00.00Z"),ZoneOffset.UTC);
        //Instant nowFixed = Instant.now(fixedClock);

        // Now show always be greater than the create date from our Flyway script so 4 records should be returned
        Instant now = Instant.now();
        mvc.perform(get("/companies/search/findByCreatedAtBefore").param("before",now.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(4)));

        // 25 years earlier, no data should be present, ChronoUnit.YEARS is not supported for whatever reason.
        now = Instant.now().minus(9125, ChronoUnit.DAYS);
        mvc.perform(get("/companies/search/findByCreatedAtBefore").param("before",now.toString()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$._embedded.companies", hasSize(0)));
    }

}
