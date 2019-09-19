package com.example.DataAnalysisSpring.controller;
import com.example.DataAnalysisSpring.InvalidUrlException;
import com.example.DataAnalysisSpring.STAXParser;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.example.DataAnalysisSpring.models.*;

import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
public class AnalyzerController {

    @PostMapping(value = "/analyze")
    public ResponseEntity<AnalysisResponse> Analyze(@RequestBody AnalysisRequest inputPayload) {
        AnalysisResponse response = new AnalysisResponse();
        response.setAnalyseDate(LocalDateTime.now().format(DateTimeFormatter.ISO_LOCAL_DATE_TIME));
        String urlString = inputPayload.getUrl();
        try {
            URL url = new URL(urlString);
            url.toURI();
            response.setDetails(STAXParser.parse(url));
        }
        catch (Exception e) {
            throw new InvalidUrlException(urlString);
        }
        return new ResponseEntity<AnalysisResponse>(response, HttpStatus.OK);
    }
}
