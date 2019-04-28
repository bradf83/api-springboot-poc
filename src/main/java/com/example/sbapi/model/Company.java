package com.example.sbapi.model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String code;
    private String officialName;
    private String commonName;

    public Company(String code, String officialName, String commonName) {
        this.code = code;
        this.officialName = officialName;
        this.commonName = commonName;
    }
}
