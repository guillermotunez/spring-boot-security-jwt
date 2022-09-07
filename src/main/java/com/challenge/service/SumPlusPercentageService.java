package com.challenge.service;

import com.challenge.controller.response.SumPlusPercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SumPlusPercentageService {

    private PercentageService percentageService;

    public SumPlusPercentageService(@Autowired PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public SumPlusPercentage sumPlusPercentage(Double a, Double b) {

        SumPlusPercentage sumPlusPercentage = new SumPlusPercentage();
        sumPlusPercentage.setResult( (a+b) * (1+(percentageService.getPercentage()/100)) );
        return sumPlusPercentage;
    }
}
