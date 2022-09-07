package com.challenge.service;

import com.challenge.thirdparty.PercentagesApiCallImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PercentageService {

    private final PercentagesApiCallImpl percentagesApiCall;

    public PercentageService(@Autowired PercentagesApiCallImpl percentagesApiCall) {
        this.percentagesApiCall = percentagesApiCall;
    }

    public Double getPercentage() {
        return percentagesApiCall.getSumPercentage().getPercentage();
    }
}
