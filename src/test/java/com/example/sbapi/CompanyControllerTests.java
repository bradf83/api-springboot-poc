package com.example.sbapi;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.hasSize;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class CompanyControllerTests {

    @Autowired
    private MockMvc mvc;

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
}
