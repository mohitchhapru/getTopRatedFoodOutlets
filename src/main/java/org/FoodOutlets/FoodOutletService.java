package org.FoodOutlets;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class FoodOutletService {

    public static HttpURLConnection getConnectionData(URL url) throws Exception {
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Accept", "application/json");

        int responseCode = conn.getResponseCode();
        if (responseCode != HttpURLConnection.HTTP_OK) {
            throw new RuntimeException("Failed : HTTP error code : " + responseCode);
        }
        return conn;
    }

    public static JsonNode getFoodOutletsForGivenCityAndPage(String city, int pageNumber) throws Exception {
        URL url = Constants.getFoodOutletsUrl(city, pageNumber);
        HttpURLConnection conn = getConnectionData(url);
        BufferedReader br = new BufferedReader(new InputStreamReader((conn.getInputStream())));
        StringBuilder response = new StringBuilder();
        String output;
        while ((output = br.readLine()) != null) {
            response.append(output);
        }
        JsonNode json = new ObjectMapper().readTree(response.toString());
        br.close();
        conn.disconnect();
        return json;
    }

}
