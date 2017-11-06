package com.liveasy.demo.service.mapService;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.liveasy.demo.model.House;
import com.liveasy.demo.service.houseService.HouseService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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


@Slf4j
@Service
public class MapServiceImpl {
    private final HouseService houseService;

    @Autowired
    public MapServiceImpl(HouseService houseService) {
        this.houseService = houseService;
    }

    public static boolean isInteger(String str) {
        if (str == null) {
            return false;
        }
        int length = str.length();
        if (length == 0) {
            return false;
        }
        int i = 0;
        if (str.charAt(0) == '-') {
            if (length == 1) {
                return false;
            }
            i = 1;
        }
        for (; i < length; i++) {
            char c = str.charAt(i);
            if (c < '0' || c > '9') {
                return false;
            }
        }
        return true;
    }


    public void updateHouseById(Long l){
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey("AIzaSyBlcxyPzDn8D8lObVhl0uPd9DK0m1RoUlM")
                .build();
        House house = houseService.findByHouseId(l);

        try{

            GeocodingResult[] results =  GeocodingApi.geocode(context,
                house.getFullAddress()).await();
            Gson gson = new GsonBuilder().setPrettyPrinting().create();


            house.getLocation().setCity((results[0].addressComponents[3].longName));
            house.getLocation().setPostalCode((results[0].addressComponents[7].longName));
            house.getLocation().setLat(results[0].geometry.location.lat);
            house.getLocation().setLng(results[0].geometry.location.lng);

            houseService.saveHouse(house);
        } catch (Exception e){}

    }




    public void write() {

        try {

            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

                // root elements
                Document doc = docBuilder.newDocument();
                Element rootElement = doc.createElement("markers");
                doc.appendChild(rootElement);

            for (House house : houseService.getAllHouses()) {
                // marker elements
                Element marker = doc.createElement("marker");
                rootElement.appendChild(marker);

                marker.setAttribute("id", house.getId().toString());
                marker.setAttribute("name", house.getUser().getFirstName() + " " + house.getUser().getLastName());
                marker.setAttribute("address", house.getLocation().getAddress() + ", " + house.getLocation().getCity());
                marker.setAttribute("lat", String.valueOf(house.getLocation().getLat()));
                marker.setAttribute("lng", String.valueOf(house.getLocation().getLng()));
                marker.setAttribute("type", "restaurant");
            }



            // write the content into xml file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);

            //todo have a more robust path name scheme here
            //StreamResult result = new StreamResult(new File("/resources/location.xml"));
            StreamResult result = new StreamResult(new File(
                    "//Users//simon//IdeaProjects//liveasyDemo//src//main//resources//location//location.xml"));


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
