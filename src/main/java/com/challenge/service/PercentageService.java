package com.challenge.service;

import com.challenge.thirdparty.PercentagesApiCallImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;

@Service
public class PercentageService {

    public static final String API_CACHE_KEY = "apiPercentage";

    private final PercentagesApiCallImpl percentagesApiCall;

    public PercentageService(@Autowired PercentagesApiCallImpl percentagesApiCall) {
        this.percentagesApiCall = percentagesApiCall;
    }

    @Cacheable( value = "percentageApiCache", key = "#root.target.API_CACHE_KEY")
    public Double getPercentage(){
        return setPercentage();
    }

    @Caching(put = @CachePut( value = "percentageApiCache", key = "#root.target.API_CACHE_KEY"))
    private Double setPercentage() {
        return percentagesApiCall.getSumPercentage().getPercentage();
    }
}
