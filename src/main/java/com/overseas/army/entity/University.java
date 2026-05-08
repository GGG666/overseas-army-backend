package com.overseas.army.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import java.math.BigDecimal;

@Entity
@Table(name = "university")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class University {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name_cn", length = 100)
    private String nameCn;

    @Column(name = "name_en", length = 200)
    private String nameEn;

    @Column(length = 10)
    private String country;

    @Column
    private Integer rank;

    @Column(name = "logo_url", length = 500)
    private String logoUrl;

    @Column(length = 50)
    private String location;

    @Column(length = 200)
    private String website;

    @Column(name = "background_image", length = 500)
    private String backgroundImage;

    @Column(name = "school_type", length = 20)
    private String schoolType;

    @Column(name = "establish_year", length = 10)
    private String establishYear;

    @Column(name = "student_num", length = 20)
    private String studentNum;

    @Column(name = "international_ratio", length = 20)
    private String internationalRatio;

    @Column(name = "description", columnDefinition = "TEXT")
    private String description;

    @Column(name = "admission_requirement", columnDefinition = "TEXT")
    private String admissionRequirement;

    @Column(name = "tuition_range", length = 100)
    private String tuitionRange;

    @Column(name = "scholarship_desc", length = 500)
    private String scholarshipDesc;

    @Column(name = "famous_alumni", columnDefinition = "TEXT")
    private String famousAlumni;

    @Column(name = "ranking_info", length = 500)
    private String rankingInfo;
}