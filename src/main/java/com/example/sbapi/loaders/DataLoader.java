package com.example.sbapi.loaders;

import com.example.sbapi.model.Company;
import com.example.sbapi.model.Employee;
import com.example.sbapi.model.Owner;
import com.example.sbapi.model.Product;
import com.example.sbapi.repository.CompanyRepository;
import com.example.sbapi.repository.EmployeeRepository;
import com.example.sbapi.repository.OwnerRepository;
import com.example.sbapi.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@RequiredArgsConstructor
@Component
public class DataLoader implements ApplicationRunner {

    private final CompanyRepository companyRepository;
    private final OwnerRepository ownerRepository;
    private final EmployeeRepository employeeRepository;
    private final ProductRepository productRepository;

    @Override
    public void run(ApplicationArguments args) throws Exception {

        Owner sterling = this.ownerRepository.save(new Owner("Sterling", "Archer"));
        Owner malory = this.ownerRepository.save(new Owner("Malory", "Archer"));

        Company abc = this.companyRepository.save(new Company("ABCD", "The Alphabet", true, false, sterling));
        Company abba = this.companyRepository.save(new Company("ABBA", "The Abba Company", true, false, malory));
        Company fake = this.companyRepository.save(new Company("FAKE", "Fake Company", true, true, malory));
        Company exmp = this.companyRepository.save(new Company("EXMP", "Example", false, false, malory));

        this.employeeRepository.save(new Employee("Sterling", "Archer", 100000L, "CEO", "S-10", abc));
        this.employeeRepository.save(new Employee("Cyril", "Figgis", 200000L, "Manager", "S-8", abc));
        this.employeeRepository.save(new Employee("Pamela", "Poovey", 200000L, "Analyst", "S-10", abc));

        this.employeeRepository.save(new Employee("Malory", "Archer", 200000L, "CEO", "S-10", abba));
        this.employeeRepository.save(new Employee("Cheryl", "Tunt", 200000L, "Director", "S-10", abba));
        this.employeeRepository.save(new Employee("Arthur", "Woodhouse", 200000L, "Maintenance", "S-10", abba));

        this.employeeRepository.save(new Employee("Algernop", "Kreiger", 200000L, "Doctor", "S-10", exmp));
        this.employeeRepository.save(new Employee("Lana", "Kane", 200000L, "CEO", "S-10", exmp));

        this.employeeRepository.save(new Employee("Barry", "Dylan", 200000L, "CEO", "S-10", fake));


        this.productRepository.save(new Product("Crayons", new BigDecimal("3.99"), "Keep the kid busy.", abc));
        this.productRepository.save(new Product("Diapers", new BigDecimal("24.99"), "For those unexplainable messes.", abc));
        this.productRepository.save(new Product("Powder", new BigDecimal("399.99"), "When travelling to Miami.", abc));

        this.productRepository.save(new Product("Bourbon", new BigDecimal("99.99"), "Dealing with issues.", abba));

        this.productRepository.save(new Product("TEC-9", new BigDecimal("999.99"), "Two are better than one.", exmp));
        this.productRepository.save(new Product("Grenade", new BigDecimal("49.99"), "When bullets won't do.", exmp));

        this.productRepository.save(new Product("Spare Parts", new BigDecimal("9.95"), "Mr fixit.", fake));

    }
}
