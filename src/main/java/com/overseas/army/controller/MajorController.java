package com.overseas.army.controller;

import com.overseas.army.common.ResultVO;
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
    public ResultVO<List<Major>> list(@RequestParam(required = false) Long universityId,
                                      @RequestParam(required = false) String degreeType) {
        List<Major> majors;
        if (universityId != null) {
            if (degreeType != null && !degreeType.isEmpty()) {
                majors = majorService.findByUniversityIdAndDegreeType(universityId, degreeType);
            } else {
                majors = majorService.findByUniversityId(universityId);
            }
        } else {
            majors = majorService.findAll();
        }
        return ResultVO.success(majors);
    }

    @GetMapping("/{id}")
    public ResultVO<Major> get(@PathVariable Long id) {
        Major major = majorService.findById(id);
        if (major == null) {
            return ResultVO.error("专业不存在");
        }
        return ResultVO.success(major);
    }
}