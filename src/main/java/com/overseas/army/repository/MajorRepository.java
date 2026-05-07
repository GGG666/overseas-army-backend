package com.overseas.army.repository;

import com.overseas.army.entity.Major;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface MajorRepository extends JpaRepository<Major, Long> {
    List<Major> findByUniversityId(Long universityId);
    List<Major> findByDegreeType(String degreeType);
    List<Major> findByUniversityIdAndDegreeType(Long universityId, String degreeType);
}