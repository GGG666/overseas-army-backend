package com.overseas.army.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "major")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Major {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "university_id")
    private Long universityId;

    @Column(name = "name_cn", length = 200)
    private String nameCn;

    @Column(name = "name_en", length = 300)
    private String nameEn;

    @Column(name = "degree_type", length = 20)
    private String degreeType;

    @Column(length = 20)
    private String duration;

    @Column(precision = 10, scale = 2)
    private BigDecimal tuition;

    @Column(name = "language_req", length = 500)
    private String languageReq;

    @Column(name = "academic_req", length = 500)
    private String academicReq;

    @Column(length = 50)
    private String deadline;

    @Column(columnDefinition = "TEXT")
    private String description;

    @Column(name = "career_prospect", length = 500)
    private String careerProspect;

    @Column(name = "employment_rate", length = 20)
    private String employmentRate;

    @Column(name = "tuition_unit", length = 20)
    private String tuitionUnit;
}