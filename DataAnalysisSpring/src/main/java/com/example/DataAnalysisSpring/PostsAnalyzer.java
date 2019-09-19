package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;

import java.time.*;
import java.time.format.DateTimeFormatter;

public class PostsAnalyzer {

    private ZonedDateTime firstPost; // Java 8 feature
    private ZonedDateTime lastPost; // Java 8 feature
    private int totalPosts;
    private int totalAcceptedPosts;
    private int avgScore;
    private int avgScoreSum;
    private int avgScoreCount;

    public PostsAnalyzer() { }

    public void analyzeDate(ZonedDateTime postDate) {
        analyzeFirstPost(postDate);
        analyzeLastPost(postDate);
    }

    public void incrementTotalPosts() {
        totalPosts++;
    }

    public void incrementTotalAcceptedPosts() {
        totalAcceptedPosts++;
    }

    public void countAvgScore(int score) {
        avgScoreSum += score;
        ++avgScoreCount;
        avgScore = avgScoreSum/avgScoreCount;
    }

    public Details buildDetails() {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_OFFSET_DATE_TIME; // Java 8 feature
        return new Details(firstPost.format(formatter), lastPost.format(formatter), totalPosts, totalAcceptedPosts, avgScore);
    }

    public ZonedDateTime getFirstPost() {
        return firstPost;
    }

    public ZonedDateTime getLastPost() {
        return lastPost;
    }

    public int getTotalPosts() {
        return totalPosts;
    }

    public int getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public int getAvgScore() {
        return avgScore;
    }

    private void analyzeFirstPost(ZonedDateTime postDate) {
        if(firstPost == null) {
            firstPost = postDate;
        } else if(postDate.isBefore(firstPost)) {
            firstPost = postDate;
        }
    }

    private void analyzeLastPost(ZonedDateTime postDate) {
        if(lastPost == null) {
            lastPost = postDate;
        } else if(postDate.isAfter(lastPost)) {
            lastPost = postDate;
        }
    }

}
