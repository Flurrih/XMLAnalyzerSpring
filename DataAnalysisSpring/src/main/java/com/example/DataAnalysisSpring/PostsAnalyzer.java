package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;

import java.time.*;

public class PostsAnalyzer {

    private ZonedDateTime firstPost; // Java 8 feature
    private ZonedDateTime lastPost;
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
        return new Details(DateUtilities.ConvertZonedDateTimeToString(firstPost), DateUtilities.ConvertZonedDateTimeToString(lastPost),
                            totalPosts, totalAcceptedPosts, avgScore);
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
