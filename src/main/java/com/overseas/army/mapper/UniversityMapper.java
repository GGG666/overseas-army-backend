package com.overseas.army.mapper;

import com.overseas.army.entity.University;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface UniversityMapper {
    List<University> findAll();

    List<University> findByCountry(@Param("country") String country);

    University findById(@Param("id") Long id);

    List<University> search(@Param("country") String country, @Param("search") String search);
}