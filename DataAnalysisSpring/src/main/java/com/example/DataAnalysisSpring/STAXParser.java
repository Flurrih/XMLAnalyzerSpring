package com.example.DataAnalysisSpring;

import com.example.DataAnalysisSpring.models.Details;

import javax.xml.namespace.QName;
import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.StartElement;
import javax.xml.stream.events.XMLEvent;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.time.ZonedDateTime;

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
                        ZonedDateTime creationDate = DateUtilities.ParseStringToZonedDateTime(
                                startElement.getAttributeByName(new QName("CreationDate")).getValue(),
                                                                DateUtilities.Zone.POLAND);
                        analyzer.analyzeDate(creationDate);
                        if(startElement.getAttributeByName(new QName("AcceptedAnswerId")) != null)
                            analyzer.incrementTotalAcceptedPosts();
                        analyzer.incrementTotalPosts();
                        int score = Integer.parseInt(startElement.getAttributeByName(new QName("Score")).getValue());
                        analyzer.countAvgScore(score);
                    }
                }
            }
        } catch (FileNotFoundException | XMLStreamException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return analyzer.buildDetails();
    }
}
