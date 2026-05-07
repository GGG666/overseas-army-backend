package com.overseas.army.controller;

import com.overseas.army.entity.Major;
import com.overseas.army.service.MajorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/majors")
@RequiredArgsConstructor
public class MajorController {
    private final MajorService majorService;

    @GetMapping
    public List<Major> list(@RequestParam(required = false) Long universityId,
                          @RequestParam(required = false) String degreeType) {
        if (universityId != null) {
            if (degreeType != null && !degreeType.isEmpty()) {
                return majorService.findByUniversityIdAndDegreeType(universityId, degreeType);
            }
            return majorService.findByUniversityId(universityId);
        }
        return majorService.findAll();
    }

    @GetMapping("/{id}")
    public Major get(@PathVariable Long id) {
        return majorService.findById(id);
    }
}