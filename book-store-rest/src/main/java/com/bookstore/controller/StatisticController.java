package com.bookstore.controller;

import com.bookstore.domain.StatisticDto;
import com.bookstore.domain.ResponseWrapper;
import com.bookstore.service.StatisticService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/statistics")
@RequiredArgsConstructor
public class StatisticController {

    private final StatisticService statisticService;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<StatisticDto>>> getMonthlyStatistic(Authentication principal) {
        String customerId = principal.getCredentials().toString();
        return ResponseEntity.ok(statisticService.getMonthlyStatistic(customerId));
    }
}
