package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;

public class PostsAnalyzer {

    private LocalDateTime firstPost; // Java 8 feature
    private LocalDateTime lastPost; // Java 8 feature
    private int totalPosts;
    private int totalAcceptedPosts;
    private int avgScore;
    private int avgScoreSum;
    private int avgScoreCount;

    public PostsAnalyzer() { }

    public void analyzeDate(LocalDateTime postDate) {
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
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME; // Java 8 feature
        return new Details(firstPost.format(formatter), lastPost.format(formatter), totalPosts, totalAcceptedPosts, avgScore);
    }

    public LocalDateTime getFirstPost() {
        return firstPost;
    }

    public LocalDateTime getLastPost() {
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

    private void analyzeFirstPost(LocalDateTime postDate) {
        if(firstPost == null) {
            firstPost = postDate;
        } else if(postDate.isBefore(firstPost)) {
            firstPost = postDate;
        }
    }

    private void analyzeLastPost(LocalDateTime postDate) {
        if(lastPost == null) {
            lastPost = postDate;
        } else if(postDate.isAfter(lastPost)) {
            lastPost = postDate;
        }
    }

}
