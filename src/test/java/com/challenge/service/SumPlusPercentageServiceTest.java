package com.challenge.service;

import com.challenge.controller.response.SumPlusPercentage;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class SumPlusPercentageServiceTest {

    @Mock
    private PercentageService percentageService;
    @InjectMocks
    private MathService unitService;

    @Test
    public void sumPlusPercentageTest() {

        when(percentageService.getPercentage()).thenReturn(10.0);

        SumPlusPercentage sumPlusPercentage = unitService.sumPlusPercentage(4.5, 5.5);

        verify(percentageService, times(1)).getPercentage();

        assertNotNull(sumPlusPercentage);
        assertEquals(11.0, sumPlusPercentage.getResult(), 0);
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumPlusPercentagePrimerParametroNoPresenteTest() {

        SumPlusPercentage sumPlusPercentage = unitService.sumPlusPercentage(null, 5.5);

        verify(percentageService, times(1)).getPercentage();
    }

    @Test(expected = IllegalArgumentException.class)
    public void sumPlusPercentageSegundoParametroNoPresenteTest() {

        SumPlusPercentage sumPlusPercentage = unitService.sumPlusPercentage(4.5, null);

        verify(percentageService, times(1)).getPercentage();
    }
}
