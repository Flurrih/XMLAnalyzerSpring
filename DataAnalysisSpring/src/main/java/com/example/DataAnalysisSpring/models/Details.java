package com.example.DataAnalysisSpring.models;

public final class Details {
    String firstPost;
    String lastPost;
    int totalPosts;
    int totalAcceptedPosts;
    int avgScore;

    public Details(String firstPost, String lastPost, int totalPosts, int totalAcceptedPosts, int avgScore) {
        this.firstPost = firstPost;
        this.lastPost = lastPost;
        this.totalPosts = totalPosts;
        this.totalAcceptedPosts = totalAcceptedPosts;
        this.avgScore = avgScore;
    }

    public String getFirstPost() {
        return firstPost;
    }

    public String getLastPost() {
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
}