package org.FoodOutlets;

import com.fasterxml.jackson.databind.JsonNode;

import java.util.ArrayList;
import java.util.List;

public class FoodOutletApp {
    static FoodOutletService foodOutletService = new FoodOutletService();

    public static List<FoodOutlet> getFoodOutletsForCity(String city) throws Exception {
        int page = 0;
        int totalPages = 1;
        List<FoodOutlet> allOutlets = new ArrayList<>();

        do {
            page += 1;
            JsonNode response = foodOutletService.getFoodOutletsForGivenCityAndPage(city, page);
            page = response.get("page").asInt();
            totalPages = response.get("total_pages").asInt();

            response.get("data").forEach(outletNode -> {
                int id = outletNode.get("id").asInt();
                String cityName = outletNode.get("city").asText();
                String name = outletNode.get("name").asText();
                String estimatedCost = outletNode.get("estimated_cost").asText();
                JsonNode userRatingNode = outletNode.get("user_rating");
                double averageRating = userRatingNode.get("average_rating").asDouble();
                int votes = userRatingNode.get("votes").asInt();
                UserRating userRating = new UserRating(averageRating, votes);
                FoodOutlet foodOutlet = new FoodOutlet(id, cityName, name, estimatedCost, userRating);
                allOutlets.add(foodOutlet);
            });

        } while (page < totalPages);
        return allOutlets;
    }

    public static List<FoodOutlet> getSortedFoodOutletsForCity(String city) throws Exception {
        List<FoodOutlet> outlets = getFoodOutletsForCity(city);
        outlets.sort((o1, o2) -> {
            int ratingCompare = Double.compare(o2.getUser_rating().getAverage_rating(), o1.getUser_rating().getAverage_rating());
            if (ratingCompare != 0) {
                return ratingCompare;
            }
            return o1.getName().compareTo(o2.getName());
        });
        return outlets;
    }

    public static List<String> getTopRatedFoodOutlets(String city, int topN) throws Exception {
        List<FoodOutlet> sortedOutlets = getSortedFoodOutletsForCity(city);
        List<String> topOutlets = new ArrayList<>();
        for (FoodOutlet outlet : sortedOutlets) {
            topOutlets.add(outlet.getName());
            if (topN == 1) break;
            topN--;
        }
        return topOutlets;
    }

    public static void main(String[] args) throws Exception {
        System.out.println(getTopRatedFoodOutlets("Seattle", 3));
    }
}
