package com.example.sbapi;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.CompanyRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class CompanyRepositoryTests {
    @Autowired
    EntityManager entityManager;

    @Autowired
    CompanyRepository companyRepository;

    @Test
    public void whenUpdatingCompanyCode(){
        Company abcdCompany = this.companyRepository.findById(1L).get();
        assertThat(abcdCompany.getCode()).isEqualTo("ABCD");
        abcdCompany.setCode("SOMETHING");
        abcdCompany = this.companyRepository.saveAndFlush(abcdCompany);
        this.entityManager.refresh(abcdCompany);
        assertThat(abcdCompany.getCode()).isEqualTo("SOMETHING");
    }
}
