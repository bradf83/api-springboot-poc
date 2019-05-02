package com.example.sbapi.model;

import com.example.sbapi.model.common.CommonProperties;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@NoArgsConstructor
@Getter
@Setter
public class Company extends CommonProperties {
    private String code;
    private String name;
    private boolean chargesGST;
    private boolean chargesPST;

    @ManyToOne(optional = false)
    private Owner owner;

    public Company(String code, String name, boolean chargesGST, boolean chargesPST, Owner owner) {
        this.code = code;
        this.name = name;
        this.chargesGST = chargesGST;
        this.chargesPST = chargesPST;
        this.owner = owner;
    }
}
