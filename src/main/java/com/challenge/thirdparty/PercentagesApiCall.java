package com.challenge.thirdparty;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface PercentagesApiCall {
	
	@GET(PercentagesEndpoints.ENDPOINT_SUM_PERCENTAGE)
    @Headers({"Content-Type: application/json", "User-Agent: Challenge"})
    Call<PercentageDto> getSumPercentage();

}
