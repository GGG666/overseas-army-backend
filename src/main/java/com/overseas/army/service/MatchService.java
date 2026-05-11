package com.overseas.army.service;

import com.overseas.army.dto.MatchRequest;
import com.overseas.army.dto.MatchResult;
import com.overseas.army.entity.Major;
import com.overseas.army.entity.University;
import com.overseas.army.mapper.MajorMapper;
import com.overseas.army.mapper.UniversityMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MatchService {
    private final UniversityMapper universityMapper;
    private final MajorMapper majorMapper;

    public List<MatchResult> match(MatchRequest request) {
        List<University> universities = universityMapper.findAll();
        if (request.getCountry() != null && !request.getCountry().isEmpty()) {
            universities = universities.stream()
                    .filter(u -> u.getCountry().equals(request.getCountry()))
                    .toList();
        }

        List<MatchResult> results = new ArrayList<>();
        for (University university : universities) {
            List<Major> majors = majorMapper.findByUniversityId(university.getId());
            if (request.getDegreeType() != null && !request.getDegreeType().isEmpty()) {
                majors = majors.stream()
                        .filter(m -> m.getDegreeType().equals(request.getDegreeType()))
                        .toList();
            }
            if (request.getMinGpa() != null) {
                majors = majors.stream()
                        .filter(m -> parseGpa(m.getAcademicReq()) >= request.getMinGpa())
                        .toList();
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