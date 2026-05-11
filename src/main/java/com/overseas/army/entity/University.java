package com.overseas.army.entity;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class University {
    private Long id;
    private String nameCn;
    private String nameEn;
    private String country;
    private Integer rank;
    private String logoUrl;
    private String location;
    private String website;
    private String backgroundImage;
    private String schoolType;
    private String establishYear;
    private String studentNum;
    private String internationalRatio;
    private String description;
    private String admissionRequirement;
    private String tuitionRange;
    private String scholarshipDesc;
    private String famousAlumni;
    private String rankingInfo;
}