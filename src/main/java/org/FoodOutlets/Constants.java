package org.FoodOutlets;
import java.net.MalformedURLException;
import java.net.URL;

public class Constants {
    public static final String FOOD_OUTLETS_URL = "https://jsonmock.hackerrank.com/api/food_outlets?city=%s&page=%s";

    public static URL getFoodOutletsUrl(String city, int page) throws MalformedURLException {
        return new URL(String.format(FOOD_OUTLETS_URL, city, page));
    }
}
