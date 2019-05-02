package com.example.sbapi.model;

import com.example.sbapi.model.common.CommonProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import java.math.BigDecimal;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Product extends CommonProperties {
    private String name;
    private BigDecimal price;
    private String comments;

    @ManyToOne(optional = false)
    private Company company;

    public Product(String name, BigDecimal price, String comments, Company company) {
        this.name = name;
        this.price = price;
        this.comments = comments;
        this.company = company;
    }
}
