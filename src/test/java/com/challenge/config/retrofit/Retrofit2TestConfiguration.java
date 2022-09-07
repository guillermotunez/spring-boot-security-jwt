package com.challenge.config.retrofit;

import com.challenge.thirdparty.PercentagesApiCall;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

@Configuration
@Profile("test")
public class Retrofit2TestConfiguration extends Retrofit2TestBaseConfiguration{

	@Bean(name = "percentageRetrofitTest")
    public Retrofit percentageRetrofitTest(@Qualifier("okHttpClientTest") OkHttpClient testClient, MockWebServer mockWebServer) {
        return new Retrofit.Builder()
                .baseUrl(mockWebServer.url("").toString())
                .addConverterFactory(JacksonConverterFactory.create())
                .client(testClient)
                .build();
    }

    @Bean(name = "percentageApiCallTest")
    public PercentagesApiCall apiCallTest(@Qualifier("percentageRetrofitTest") Retrofit testRetrofit) {
        return testRetrofit.create(PercentagesApiCall.class);
    }

}

