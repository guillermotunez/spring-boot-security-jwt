package com.challenge.config.retrofit;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.ResponseBody;
import okhttp3.internal.http.HttpMethod;
import okio.Buffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

public class Retrofit2LoggingInterceptor implements Interceptor {

    private static final Logger LOGGER = LoggerFactory.getLogger(Retrofit2LoggingInterceptor.class);
    
    @Override
    public Response intercept(Chain chain) throws IOException {
        Request request = chain.request();

        long t1 = System.nanoTime();
        LOGGER.debug("\nSending Retrofit request {} on chain connection {}.\n Headers: {}\n Request body: {}\n", request.url(),
                chain.connection(), request.headers(), getRequestBody(request));

        Response response = chain.proceed(request);

        // body.string() can be called only once!!!
        String bodyString = response.body().string();

        long t2 = System.nanoTime();
        LOGGER.debug("\nReceived Retrofit response for {} in {}.\nCode: {}\n Headers: {} \n Body: {}", response.request().url(),
                (t2 - t1) / 1e6d, response.code(), response.headers(), bodyString);

        return response.newBuilder().body(ResponseBody.create(response.body().contentType(), bodyString)).build();
    }

    public String getRequestBody(final Request request) {
        try {
            final Buffer buffer = new Buffer();
            if (HttpMethod.requiresRequestBody(request.method())) {
            	request.body().writeTo(buffer);
            	return buffer.readUtf8();
            }
            buffer.close();
            return null;
        } catch (final IOException e) {
            return "did not work";
        }
    }
    
}
