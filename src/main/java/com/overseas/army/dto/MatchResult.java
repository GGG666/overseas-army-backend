package com.overseas.army.dto;

import com.overseas.army.entity.Major;
import com.overseas.army.entity.University;
import lombok.Data;
import java.util.List;

@Data
public class MatchResult {
    private University university;
    private List<Major> majors;
}