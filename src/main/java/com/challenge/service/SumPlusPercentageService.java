package com.challenge.service;

import com.challenge.controller.response.SumPlusPercentage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class SumPlusPercentageService {

    public static final String MISSING_PARAMETER = "El '%s' parámetro no está presente.";

    private PercentageService percentageService;

    public SumPlusPercentageService(@Autowired PercentageService percentageService) {
        this.percentageService = percentageService;
    }

    public SumPlusPercentage sumPlusPercentage(Double a, Double b) {
        Assert.notNull(a, String.format(MISSING_PARAMETER, "primer"));
        Assert.notNull(b, String.format(MISSING_PARAMETER, "segundo"));
        SumPlusPercentage sumPlusPercentage = new SumPlusPercentage();
        sumPlusPercentage.setResult( (a+b) * (1+(percentageService.getPercentage()/100)) );
        return sumPlusPercentage;
    }
}
