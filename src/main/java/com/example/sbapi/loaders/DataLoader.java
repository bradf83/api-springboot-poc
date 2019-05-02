package com.example.sbapi.loaders;

import com.example.sbapi.model.Company;
import com.example.sbapi.model.Owner;
import com.example.sbapi.repository.CompanyRepository;
import com.example.sbapi.repository.OwnerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final CompanyRepository companyRepository;
    private final OwnerRepository ownerRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Owner sterling = this.ownerRepository.save(new Owner("Sterling", "Archer"));
        Owner mallory = this.ownerRepository.save(new Owner("Mallory", "Archer"));

        this.companyRepository.save(new Company("ABCD", "The Alphabet", true, false, sterling));
        this.companyRepository.save(new Company("ABBA", "The Abba Company", true, false, mallory));
        this.companyRepository.save(new Company("FAKE", "Fake Company", true, true, mallory));
        this.companyRepository.save(new Company("EXMP", "Example", false, false, mallory));
    }
}
