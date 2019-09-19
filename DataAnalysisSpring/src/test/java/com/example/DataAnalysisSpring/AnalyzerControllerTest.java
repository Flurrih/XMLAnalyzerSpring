package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.controller.AnalyzerController;
import com.example.DataAnalysisSpring.models.AnalysisResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hamcrest.Matchers;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertThat;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.hamcrest.Matchers.*;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = AnalyzerController.class)
public class AnalyzerControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    PostsAnalyzer postAnalyzer;

    @Test
    public void shouldSuccessfullySendPostRequest() throws Exception {
        mockMvc.perform(post("/analyze")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{ \"url\": \"https://raw.githubusercontent.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml\" }"))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldSendInvalidUrlPostRequest() throws Exception {
        mockMvc.perform(post("/analyze")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{ \"url\": \"https://raw.githubuserconte    nt.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml\" }"))
                .andExpect(status().isBadRequest());
    }

    @Test
    public void verifyCorrectAnalysisResponseWhenPostSuccesfull() throws Exception {
        MvcResult result = mockMvc.perform(post("/analyze")
                .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .content("{ \"url\": \"https://raw.githubusercontent.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml\" }"))
                .andExpect(status().isOk())
                .andReturn();

        List<String> expectedStrings = Arrays.asList("analyseDate", "details", "firstPost", "lastPost", "totalPosts", "totalAcceptedPosts", "avgScore");
        String resultString = result.getResponse().getContentAsString();
        assertThat(resultString, Matchers.stringContainsInOrder(expectedStrings));

    }

}
