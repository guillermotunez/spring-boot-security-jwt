package com.challenge.thirdparty;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.mockwebserver.MockResponse;
import okhttp3.mockwebserver.MockWebServer;
import okhttp3.mockwebserver.RecordedRequest;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest
@ActiveProfiles("test")
public class PercentageApiCallImplTest {

    @Autowired
    private PercentagesApiCallImpl percentagesApiCallImpl;

    @Autowired
    private MockWebServer mockWebServer;

    @Test
    public void postAuditarOperacion() throws InterruptedException, IOException {
        MockResponse mockResponse = new MockResponse();
        mockResponse.setBody("{\"percentage\": 10.0}");
        mockWebServer.enqueue(mockResponse);

        PercentageDto response = percentagesApiCallImpl.getSumPercentage();
        RecordedRequest request = mockWebServer.takeRequest();

        assertEquals("/third-party/sum-percentage", request.getPath());

        assertEquals(10.0, response.getPercentage(), 0);
    }
}
