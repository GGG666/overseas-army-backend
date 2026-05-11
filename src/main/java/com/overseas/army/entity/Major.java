package com.overseas.army.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    private Long id;
    private Long universityId;
    private String nameCn;
    private String nameEn;
    private String degreeType;
    private String duration;
    private BigDecimal tuition;
    private String languageReq;
    private String academicReq;
    private String deadline;
    private String description;
    private String careerProspect;
    private String employmentRate;
    private String tuitionUnit;
}