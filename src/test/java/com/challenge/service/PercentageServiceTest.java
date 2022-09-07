package com.challenge.service;

import com.challenge.thirdparty.PercentageDto;
import com.challenge.thirdparty.PercentagesApiCallImpl;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class PercentageServiceTest {

    @Mock
    private PercentagesApiCallImpl apiCall;
    @InjectMocks
    private PercentageService unitService;

    @Test
    public void getPercentageTest() {

        PercentageDto percentageDto = new PercentageDto();
        percentageDto.setPercentage(10.0);
        when(apiCall.getSumPercentage()).thenReturn(percentageDto);

        Double result = unitService.getPercentage();

        verify(apiCall, times(1)).getSumPercentage();

        assertNotNull(result);
        assertEquals(10.0, result, 0);
    }

}
