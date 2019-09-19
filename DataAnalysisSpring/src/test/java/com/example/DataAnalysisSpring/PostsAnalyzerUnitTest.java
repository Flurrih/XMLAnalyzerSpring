package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

import static org.junit.Assert.assertEquals;

public class PostsAnalyzerUnitTest {
    private PostsAnalyzer postsAnalyzer;

    @Before
    public void init() {
        postsAnalyzer = new PostsAnalyzer();
    }

    @Test
    public void shouldSetFirstAndLastPost() {
        ZonedDateTime time = ZonedDateTime.now();
        postsAnalyzer.analyzeDate(time);
        assertEquals(time, postsAnalyzer.getFirstPost());
        assertEquals(time, postsAnalyzer.getLastPost());
    }

    @Test
    public void shouldSetFirstAndLastPostDifferent() {
        ZonedDateTime timeBefore = ZonedDateTime.now().minusYears(1);
        ZonedDateTime timeAfter = ZonedDateTime.now().plusYears(1);
        postsAnalyzer.analyzeDate(timeBefore);
        postsAnalyzer.analyzeDate(timeAfter);
        assertEquals(timeBefore, postsAnalyzer.getFirstPost());
        assertEquals(timeAfter, postsAnalyzer.getLastPost());
    }

    @Test
    public void shouldIncrementTotalPosts() {
        postsAnalyzer.incrementTotalPosts();
        assertEquals(1, postsAnalyzer.getTotalPosts());
    }

    @Test
    public void shouldIncrementAcceptedPosts() {
        postsAnalyzer.incrementTotalAcceptedPosts();
        assertEquals(1, postsAnalyzer.getTotalAcceptedPosts());
    }

    @Test
    public void shouldCountCorrectAvarageScore() {
        postsAnalyzer.countAvgScore(5);
        postsAnalyzer.countAvgScore(1);
        postsAnalyzer.countAvgScore(-1);
        postsAnalyzer.countAvgScore(10);
        assertEquals(3, postsAnalyzer.getAvgScore());
    }

    @Test
    public void shouldBuildCorrectDetails() {
        ZonedDateTime time = ZonedDateTime.now();
        String timeString = time.format(DateTimeFormatter.ISO_OFFSET_DATE_TIME);
        postsAnalyzer.analyzeDate(time);
        postsAnalyzer.incrementTotalPosts();
        postsAnalyzer.incrementTotalAcceptedPosts();
        postsAnalyzer.countAvgScore(5);
        Details returnedDetails = postsAnalyzer.buildDetails();
        assertEquals(returnedDetails.getFirstPost(), timeString);
        assertEquals(returnedDetails.getTotalPosts(), 1);
        assertEquals(returnedDetails.getTotalAcceptedPosts(), 1);
        assertEquals(returnedDetails.getAvgScore(), 5);
    }

}
