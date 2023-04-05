package com.example.eotinish.dto.req;

import lombok.Data;

@Data
public class Applicant {

    public String applicantType;
    public String personFirstName;
    public String personSecondName;

    public String personMiddleName;
    public String personFactAddress;
    public String personLawAddress;

    //// ...............

    public String companyName;
    public String companyLawAddress;
    public String companyFactAddress;
    public String companyHeadFirstName;
    public String companyHeadSecondName;
    public String companyHeadMiddleName;


}
