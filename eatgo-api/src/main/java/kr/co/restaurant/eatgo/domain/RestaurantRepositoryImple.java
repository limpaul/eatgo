package kr.co.restaurant.eatgo.domain;

import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class RestaurantRepositoryImple implements RestaurantRepository {
    private List<Restaurant> restaurants = new ArrayList<>();

    public RestaurantRepositoryImple() {
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        restaurants.add(new Restaurant(2021L, "Cyber Food", "Seoul"));
    }

    @Override
    public List<Restaurant> findAll() {
        return restaurants;
    }
    @Override
    public Restaurant findById(Long id){
        Restaurant restaurant = restaurants.stream()
                .filter(r -> r.getId() == id )
                .findFirst().orElse(null);
        return restaurant;
    }

    @Override
    public Restaurant save(Restaurant restaurant) {
        restaurant.setId(1234L);
        restaurants.add(restaurant);
        return restaurant;
    }
}

