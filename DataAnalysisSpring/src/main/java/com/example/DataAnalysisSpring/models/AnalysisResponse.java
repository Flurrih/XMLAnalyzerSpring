package com.example.DataAnalysisSpring.models;


public class AnalysisResponse {
    String analyseDate;
    Details details;

    public String getAnalyseDate() {
        return analyseDate;
    }

    public void setAnalyseDate(String analyseDate) {
        this.analyseDate = analyseDate;
    }

    public Details getDetails() {
        return details;
    }

    public void setDetails(Details details) {
        this.details = details;
    }
}
