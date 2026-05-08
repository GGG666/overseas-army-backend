package com.overseas.army.service;

import com.overseas.army.entity.University;
import com.overseas.army.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UniversityService {
    private final UniversityRepository universityRepository;

    public List<University> findAll() {
        return universityRepository.findAll();
    }

    public List<University> search(String country, String search) {
        return universityRepository.search(country, search);
    }

    public University findById(Long id) {
        return universityRepository.findById(id).orElse(null);
    }
}