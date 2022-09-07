package com.challenge.config.retrofit;

import okhttp3.OkHttpClient;
import okhttp3.mockwebserver.MockWebServer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;

import java.util.concurrent.TimeUnit;

public class Retrofit2TestBaseConfiguration {

    @Value("${retrofit.percentage.connect.timeout}")
    private Integer connectTimeout;

    @Value("${retrofit.percentage.read.timeout}")
    private Integer readTimeout;

    @Value("${retrofit.percentage.write.timeout}")
    private Integer writeTimeout;

    @Bean
    public MockWebServer mockWebServer() {
        return new MockWebServer();
    }

    @Bean(name = "okHttpClientTest")
    public OkHttpClient okHttpClient() {
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(new Retrofit2LoggingInterceptor())
                .build();
    }
}
