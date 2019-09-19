package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;

import java.net.MalformedURLException;
import java.net.URL;

import static org.junit.Assert.assertEquals;


public class STAXParserUnitTest {
    @Test
    public void shouldReturnCorrectDetailsFromXML() throws MalformedURLException {
        URL url = new URL("https://raw.githubusercontent.com/Flurrih/XMLAnalyzerSpring/master/testSample.xml");

        Details details = STAXParser.parse(url);

        assertEquals("2015-07-14T18:39:27.757+02:00", details.getFirstPost());
        assertEquals("2015-07-14T22:28:24.197+02:00", details.getLastPost());
        assertEquals(9, details.getTotalPosts());
        assertEquals(1, details.getTotalAcceptedPosts());
        assertEquals(2, details.getAvgScore());
    }
}
