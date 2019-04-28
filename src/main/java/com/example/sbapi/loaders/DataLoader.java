package com.example.sbapi.loaders;

import com.example.sbapi.model.Company;
import com.example.sbapi.repository.CompanyRepository;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements ApplicationRunner {

    private CompanyRepository companyRepository;

    public DataLoader(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        this.companyRepository.save(new Company("ABCD", "The Alphabet", "ABCs"));
        this.companyRepository.save(new Company("ABBA", "The Abba Company", "Abba"));
        this.companyRepository.save(new Company("FAKE", "Fake Company", "Fakes"));
        this.companyRepository.save(new Company("EXMP", "Example", "Examples"));
    }
}
