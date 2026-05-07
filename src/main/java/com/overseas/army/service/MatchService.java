package com.overseas.army.service;

import com.overseas.army.dto.MatchRequest;
import com.overseas.army.dto.MatchResult;
import com.overseas.army.entity.Major;
import com.overseas.army.entity.University;
import com.overseas.army.repository.MajorRepository;
import com.overseas.army.repository.UniversityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final UniversityRepository universityRepository;
    private final MajorRepository majorRepository;

    public List<MatchResult> match(MatchRequest request) {
        List<University> universities = universityRepository.findAll();
        if (request.getCountry() != null && !request.getCountry().isEmpty()) {
            universities = universities.stream()
                    .filter(u -> u.getCountry().equals(request.getCountry()))
                    .collect(Collectors.toList());
        }

        List<MatchResult> results = new ArrayList<>();
        for (University university : universities) {
            List<Major> majors = majorRepository.findByUniversityId(university.getId());
            if (request.getDegreeType() != null && !request.getDegreeType().isEmpty()) {
                majors = majors.stream()
                        .filter(m -> m.getDegreeType().equals(request.getDegreeType()))
                        .collect(Collectors.toList());
            }
            if (request.getMinGpa() != null) {
                majors = majors.stream()
                        .filter(m -> parseGpa(m.getAcademicReq()) >= request.getMinGpa())
                        .collect(Collectors.toList());
            }

            if (!majors.isEmpty()) {
                MatchResult result = new MatchResult();
                result.setUniversity(university);
                result.setMajors(majors);
                results.add(result);
            }
        }
        return results;
    }

    private double parseGpa(String academicReq) {
        if (academicReq == null || academicReq.isEmpty()) return 0;
        try {
            String num = academicReq.replaceAll("[^0-9.]", "");
            return Double.parseDouble(num.isEmpty() ? "0" : num.split("\\.")[0]);
        } catch (Exception e) {
            return 0;
        }
    }
}