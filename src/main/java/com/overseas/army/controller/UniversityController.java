package com.overseas.army.controller;

import com.overseas.army.common.ResultVO;
import com.overseas.army.entity.University;
import com.overseas.army.service.UniversityService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/universities")
@RequiredArgsConstructor
public class UniversityController {
    private final UniversityService universityService;

    @GetMapping
    public ResultVO<List<University>> list(
            @RequestParam(required = false) String country,
            @RequestParam(required = false) String search) {
        List<University> universities;
        if (search != null && !search.isEmpty()) {
            universities = universityService.search(country, search);
        } else if (country != null && !country.isEmpty()) {
            universities = universityService.findByCountry(country);
        } else {
            universities = universityService.findAll();
        }
        return ResultVO.success(universities);
    }

    @GetMapping("/{id}")
    public ResultVO<University> get(@PathVariable Long id) {
        University university = universityService.findById(id);
        if (university == null) {
            return ResultVO.error("院校不存在");
        }
        return ResultVO.success(university);
    }
}