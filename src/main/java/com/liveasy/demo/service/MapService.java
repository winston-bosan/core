package com.liveasy.demo.service;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.maps.GeoApiContext;
import com.google.maps.GeocodingApi;
import com.google.maps.model.GeocodingResult;
import com.liveasy.demo.model.House;
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
public class MapService {
    private final HouseService houseService;

    @Autowired
    public MapService(HouseService houseService) {
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

    public void test(){
        log.debug("I AM ALIVE");
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


            house.setCity((results[0].addressComponents[3].longName));
            house.setPostal((results[0].addressComponents[7].longName));
            house.setLat(results[0].geometry.location.lat);
            house.setLng(results[0].geometry.location.lng);

            houseService.saveHouse(house);
        } catch (Exception e){}

    }

    //Deprecated Version 0.0
    /*
    public double[] updateHouseById(Long l){
        House house = houseService.findByHouseId(l);

        String input = house.getAddress();
        String url = "https://maps.googleapis.com/maps/api/geocode/json";
        String charset = "UTF-8";
        String address = input;
        //Assuming it is in Toronto
        //todo change this to a more generalizable version later
        address = address + ", Toronto, ON";
        address = address.replace(" ","+");
        //todo wire this from application properties
        String key = "AIzaSyBlcxyPzDn8D8lObVhl0uPd9DK0m1RoUlM";

        //Initialize Query and URL
        String query = "address=" + address + "&" + "key=" + key;
        System.out.println(query);

        //URLConnection connection = new URL(url + "?" + query).openConnection();

        try {
            URL answer = new URL(url + "?" + query);
            try {
                URLConnection connection = answer.openConnection();


                //Set Charset
                connection.setRequestProperty("Accept-Charset", charset);
                InputStream response = connection.getInputStream();

                System.out.println(response);

                try (Scanner scanner = new Scanner(response)) {
                    String responseBody = scanner.useDelimiter("\\A").next();
                    System.out.println(responseBody);
                }

                return null;

            } catch (IOException e){
                e.printStackTrace();
            }

        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;

    }

    public void updateLocationById(Long id){
        House house = houseService.findByHouseId(id);

    }
    */

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
                marker.setAttribute("address", house.getAddress() + ", " + house.getCity());
                marker.setAttribute("lat", String.valueOf(house.getLat()));
                marker.setAttribute("lng", String.valueOf(house.getLng()));
                marker.setAttribute("type", "restaurant");
            }



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
