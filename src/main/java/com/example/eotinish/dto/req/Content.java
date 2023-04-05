package com.example.eotinish.dto.req;

import lombok.Data;

import java.util.List;
@Data
public class Content {
    private Long id;
    private String sid;

    private String regNumber;
    private String currentState; //enum do!!

    private Application application;

    private String startDate;
    private String deadline;

    private Organization organization;

    private List<Executor> executors;

    private int deadlineDays;

}
