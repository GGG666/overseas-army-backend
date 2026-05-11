package com.overseas.army.service;

import com.overseas.army.entity.Major;
import com.overseas.army.mapper.MajorMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MajorService {
    private final MajorMapper majorMapper;

    public List<Major> findAll() {
        return majorMapper.findAll();
    }

    public List<Major> findByUniversityId(Long universityId) {
        return majorMapper.findByUniversityId(universityId);
    }

    public List<Major> findByUniversityIdAndDegreeType(Long universityId, String degreeType) {
        return majorMapper.findByUniversityIdAndDegreeType(universityId, degreeType);
    }

    public Major findById(Long id) {
        return majorMapper.findById(id);
    }
}