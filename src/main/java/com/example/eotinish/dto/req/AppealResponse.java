package com.example.eotinish.dto.req;

import lombok.Data;

import java.util.List;

@Data
public class AppealResponse {
    private List<Content> content;
    private int totalElements;
}
