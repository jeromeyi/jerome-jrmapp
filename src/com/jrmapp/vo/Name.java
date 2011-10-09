package com.jrmapp.vo;

import com.opensymphony.xwork2.validator.annotations.RequiredFieldValidator;
import com.opensymphony.xwork2.validator.annotations.RequiredStringValidator;
import com.opensymphony.xwork2.validator.annotations.Validations;

@Validations(requiredFields={@RequiredFieldValidator(fieldName="lastName",message="LastName错误")})
public class Name {
    private String firstName;
    private String lastName;
    public String getFirstName() {
        return firstName;
    }
    @Validations(requiredStrings={@RequiredStringValidator(fieldName="firstName",message="FirstName错误")})
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    
}