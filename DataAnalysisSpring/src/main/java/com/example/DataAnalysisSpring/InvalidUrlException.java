package com.example.DataAnalysisSpring;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class InvalidUrlException extends RuntimeException  {
    public InvalidUrlException(String exception) {
        super(exception);
    }
}
