package com.challenge.controller;

import com.challenge.controller.response.SumPlusPercentage;
import com.challenge.service.MathService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.test.web.servlet.setup.SecurityMockMvcConfigurers;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@SpringBootTest
@WebAppConfiguration
@ContextConfiguration
public class MathControllerTest {

    private static final String MATH_ENDPOINT = "/api/math/sum-plus-percentage";

    @Autowired
    private WebApplicationContext context;

    private MockMvc mockMvc;

    @Before
    public void setup() {
        mockMvc = MockMvcBuilders
                .webAppContextSetup(context)
                .apply(SecurityMockMvcConfigurers.springSecurity())
                .build();
    }

    @MockBean
    private MathService mathService;

    @Test
    @WithMockUser(username="user", roles="USER")
    public void sumPlusPercentageOK() throws Exception {

        SumPlusPercentage sumPlusPercentageResult = new SumPlusPercentage();
        sumPlusPercentageResult.setResult(11.0);
        when(mathService.sumPlusPercentage(4.5, 5.5)).thenReturn(sumPlusPercentageResult);

        MockHttpServletRequestBuilder requestBuilder = get(MATH_ENDPOINT)
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("a", "4.5")
                        .param("b", "5.5");

        mockMvc.perform(requestBuilder).andExpect(status().isOk())
                .andExpect(jsonPath("$.result", is(11.0)));
    }

    @Test
    @WithMockUser(username="user", roles="FORBIDDEN_USER")
    public void sumPlusPercentageForbidden() throws Exception {

        MockHttpServletRequestBuilder requestBuilder = get(MATH_ENDPOINT)
                .contentType(MediaType.APPLICATION_JSON)
                .param("a", "4.5")
                .param("b", "5.5");

        mockMvc.perform(requestBuilder).andExpect(status().isForbidden());
    }
}
