package com.challenge.controller;

import com.challenge.controller.response.SumPlusPercentage;
import com.challenge.service.MathService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/math")
public class MathController {

    private final MathService sumPlusPercentageService;

    public MathController(@Autowired MathService sumPlusPercentageService) {
        this.sumPlusPercentageService = sumPlusPercentageService;
    }

    @GetMapping("/sum-plus-percentage")
    @PreAuthorize("hasRole('USER') or hasRole('MODERATOR') or hasRole('ADMIN')")
    public ResponseEntity<SumPlusPercentage> sumPlusPercentage(@RequestParam("a") Double a,
                                                               @RequestParam("b") Double b) {
        return ResponseEntity.ok(sumPlusPercentageService.sumPlusPercentage(a,b));
    }

}
