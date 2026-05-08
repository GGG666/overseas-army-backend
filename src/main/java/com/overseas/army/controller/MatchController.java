package com.overseas.army.controller;

import com.overseas.army.common.ResultVO;
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
    public ResultVO<List<MatchResult>> match(@RequestBody MatchRequest request) {
        List<MatchResult> results = matchService.match(request);
        return ResultVO.success(results);
    }
}