package org.FoodOutlets;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRating {
    private double average_rating;
    private int votes;

    UserRating(double average_rating, int votes) {
        this.average_rating = average_rating;
        this.votes = votes;
    }
}
