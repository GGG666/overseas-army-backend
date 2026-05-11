package com.overseas.army.mapper;

import com.overseas.army.entity.Major;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

@Mapper
public interface MajorMapper {
    List<Major> findAll();

    List<Major> findByUniversityId(@Param("universityId") Long universityId);

    List<Major> findByUniversityIdAndDegreeType(@Param("universityId") Long universityId, @Param("degreeType") String degreeType);

    Major findById(@Param("id") Long id);
}