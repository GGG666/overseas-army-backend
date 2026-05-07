package com.overseas.army.controller;

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
    public List<University> list(@RequestParam(required = false) String country) {
        if (country != null && !country.isEmpty()) {
            return universityService.findByCountry(country);
        }
        return universityService.findAll();
    }

    @GetMapping("/{id}")
    public University get(@PathVariable Long id) {
        return universityService.findById(id);
    }
}