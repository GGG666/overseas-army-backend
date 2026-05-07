package com.overseas.army.service;

import com.overseas.army.entity.Major;
import com.overseas.army.repository.MajorRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorRepository majorRepository;

    public List<Major> findAll() {
        return majorRepository.findAll();
    }

    public List<Major> findByUniversityId(Long universityId) {
        return majorRepository.findByUniversityId(universityId);
    }

    public List<Major> findByUniversityIdAndDegreeType(Long universityId, String degreeType) {
        return majorRepository.findByUniversityIdAndDegreeType(universityId, degreeType);
    }

    public Major findById(Long id) {
        return majorRepository.findById(id).orElse(null);
    }
}