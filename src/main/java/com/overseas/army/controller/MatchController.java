package com.overseas.army.controller;

import com.overseas.army.dto.MatchRequest;
import com.overseas.army.dto.MatchResult;
import com.overseas.army.service.MatchService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/match")
@RequiredArgsConstructor
public class MatchController {
    private final MatchService matchService;

    @PostMapping
    public List<MatchResult> match(@RequestBody MatchRequest request) {
        return matchService.match(request);
    }
}