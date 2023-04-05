package com.example.eotinish.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

@Data
@Entity
public class Appeal {

    @Id
    private String regNumber;
    private String currentState;

    private String startDate;
    private String deadline;

    private String organizationNameRu;

    private String mainExecutor;

    private int deadlineDays;

    private String appealType;
    @Column(length = 4096)
    private String appealData;

    private String applicantType;
    private String personFIO;

    private String personAddress;

    private String companyName;

    private String companyHeadFIO;

    private String companyAddress;





}
