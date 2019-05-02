package com.example.sbapi.model;

import com.example.sbapi.model.common.CommonProperties;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Employee extends CommonProperties {

    private String firstName;
    private String lastName;
    private Long salary;
    private String title;
    private String position;

    @ManyToOne(optional = false)
    private Company company;

    public Employee(String firstName, String lastName, Long salary, String title, String position, Company company) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.salary = salary;
        this.title = title;
        this.position = position;
        this.company = company;
    }
}
