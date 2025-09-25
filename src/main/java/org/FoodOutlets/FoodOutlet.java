package org.FoodOutlets;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class FoodOutlet {
    private int id;
    private String city;
    private String name;
    private String estimated_cost;
    private UserRating user_rating;

    FoodOutlet(int id, String city, String name, String estimated_cost, UserRating user_rating) {
        this.id = id;
        this.city = city;
        this.name = name;
        this.estimated_cost = estimated_cost;
        this.user_rating = user_rating;
    }
}