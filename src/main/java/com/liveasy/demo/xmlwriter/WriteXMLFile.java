package com.liveasy.demo.xmlwriter;

import com.liveasy.demo.service.HouseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;

@Component
public class WriteXMLFile {
    private final HouseService houseService;

    @Autowired
    public WriteXMLFile(HouseService houseService) {
        this.houseService = houseService;
    }

    public static void write() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("markers");
                doc.appendChild(rootElement);

                // marker elements
                Element marker = doc.createElement("marker");
                rootElement.appendChild(marker);

                marker.setAttribute("id", "1");
                marker.setAttribute("name", "Simon Chen");
                marker.setAttribute("address", "8 Suncrest Drive, Toronto");
                marker.setAttribute("lat", "43.726126");
                marker.setAttribute("lng", "-79.362578");
                marker.setAttribute("type", "restaurant");


            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            //StreamResult result = new StreamResult(new File("/resources/location.xml"));
            StreamResult result = new StreamResult(new File(
                    "//Users//simon//IdeaProjects//liveasyDemo//src//main//resources//location.xml"));


            // Output to console for testing
            //result = new StreamResult(System.out);

            transformer.transform(source, result);

            System.out.println("File saved!");

        } catch (ParserConfigurationException pce) {
            pce.printStackTrace();
        } catch (TransformerException tfe) {
            tfe.printStackTrace();
        }
    }
}
