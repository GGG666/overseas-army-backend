package com.overseas.army.dto;

import lombok.Data;

@Data
public class MatchRequest {
    private Double minGpa;
    private Double minIelts;
    private String country;
    private String degreeType;
}