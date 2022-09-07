package com.challenge.thirdparty;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import retrofit2.Call;
import retrofit2.Response;

import java.io.IOException;

import static com.challenge.thirdparty.PercentagesEndpoints.ENDPOINT_SUM_PERCENTAGE;

@Service
public class PercentagesApiCallImpl {

    @Autowired
    private PercentagesApiCall percentagesApiCall;

    /**
     * Just for testing purposes!
     */
    void setRestService(PercentagesApiCall percentagesApiCall) {
        this.percentagesApiCall = percentagesApiCall;
    }

    public PercentageDto getSumPercentage() {
        Call<PercentageDto> call = percentagesApiCall.getSumPercentage();
        Response<PercentageDto> response = null;
        try {
            response = call.execute();
        } catch (IOException e) {
            // log.error
        }
        if (response != null && !response.isSuccessful()) {
            // log.error
        }
        if (response.isSuccessful()) {
            return response.body();
        } else {
            throw new RuntimeException("Not successful response from ApiCall: " + ENDPOINT_SUM_PERCENTAGE +
                    "HttpStatusCode: " + response.code() + "ErrorBody: " + response.errorBody());
        }
    }
}
