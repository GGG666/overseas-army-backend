package com.overseas.army.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

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
}