package com.example.sbapi.model.validator;

import com.example.sbapi.model.Company;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component("beforeCreateCompanyValidator")
public class BeforeCreateCompanyValidator implements Validator {
    @Override
    public boolean supports(Class<?> aClass) {
        return Company.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        ValidationUtils.rejectIfEmpty(errors, "code", null,"The code cannot be empty.");
        ValidationUtils.rejectIfEmpty(errors, "name", null,"The name cannot be empty.");
        ValidationUtils.rejectIfEmpty(errors, "owner", null,"You must select an owner for this company.");
    }
}
