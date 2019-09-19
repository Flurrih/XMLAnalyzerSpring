package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.Attribute;
import javax.xml.stream.events.EndElement;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.UnknownHostException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class STAXParser {
    public static Details parse(URL url) {
        XMLInputFactory xmlInputFactory = XMLInputFactory.newInstance();
        PostsAnalyzer analyzer = new PostsAnalyzer();
        try {
            InputStream in = url.openStream();
            XMLEventReader xmlEventReader = xmlInputFactory.createXMLEventReader(in);
            while(xmlEventReader.hasNext()) {
                XMLEvent xmlEvent = xmlEventReader.nextEvent();
                if (xmlEvent.isStartElement()){
                    StartElement startElement = xmlEvent.asStartElement();
                    if(startElement.getName().getLocalPart().equals("row")){
                        LocalDateTime creationDate = parseDate(startElement.getAttributeByName(new QName("CreationDate")).getValue());
                        analyzer.analyzeDate(creationDate);
                        if(startElement.getAttributeByName(new QName("AcceptedAnswerId")) != null)
                            analyzer.incrementTotalAcceptedPosts();
                        analyzer.incrementTotalPosts();
                        analyzer.countAvgScore(Integer.parseInt(startElement.getAttributeByName(new QName("Score")).getValue()));
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } catch (ParseException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return analyzer.buildDetails();
    }

    private static LocalDateTime parseDate(String date) throws ParseException {
        DateTimeFormatter formatter = DateTimeFormatter.ISO_LOCAL_DATE_TIME;
        return LocalDateTime.parse(date, formatter);
    }
}
