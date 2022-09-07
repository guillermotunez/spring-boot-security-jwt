package com.challenge.config.retrofit;

import com.challenge.config.NotTestProfileCondition;
import com.challenge.thirdparty.PercentagesApiCall;
import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;
import retrofit2.Retrofit;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

@Configuration
@Conditional(NotTestProfileCondition.class)
public class Retrofit2Configuration {

    private static final Logger LOGGER = LoggerFactory.getLogger(Retrofit2Configuration.class);

    @Value("${retrofit.percentage.url}")
    private String urlPercentages;

    @Value("${retrofit.percentage.connect.timeout}")
    private Integer connectTimeout;

    @Value("${retrofit.percentage.read.timeout}")
    private Integer readTimeout;
    
    @Value("${retrofit.percentage.write.timeout}")
    private Integer writeTimeout;

    @Autowired
    private ObjectMapper objectMapper;

    @Bean
    public OkHttpClient okHttpClient() {
        LOGGER.info("OkHttpClient: ConnectTimeout={} y ReadTimeout={} y WriteTimeout={} segundos.",
                connectTimeout, readTimeout, writeTimeout);
        return new OkHttpClient()
                .newBuilder()
                .connectTimeout(connectTimeout, TimeUnit.SECONDS)
                .readTimeout(readTimeout, TimeUnit.SECONDS)
                .writeTimeout(writeTimeout, TimeUnit.SECONDS)
                .addInterceptor(new Retrofit2LoggingInterceptor())
                .build();
    }

    @Bean(name = "percentageRetrofit")
    public Retrofit retrofit(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl(urlPercentages)
                .addConverterFactory(JacksonConverterFactory.create())
                .client(client)
                .build();
    }

    // Retrofit Services
	@Bean
	public PercentagesApiCall percentageApiCall(@Qualifier("percentageRetrofit") Retrofit retrofit) {
		return retrofit.create(PercentagesApiCall.class);
	}

}