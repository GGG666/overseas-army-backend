package com.overseas.army.service;

import com.overseas.army.entity.University;
import com.overseas.army.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityMapper universityMapper;

    public List<University> findAll() {
        return universityMapper.findAll();
    }

    public List<University> search(String country, String search) {
        return universityMapper.search(country, search);
    }

    public University findById(Long id) {
        return universityMapper.findById(id);
    }

    public List<University> findByCountry(String country) {
        return universityMapper.findByCountry(country);
    }
}