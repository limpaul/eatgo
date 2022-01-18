package kr.co.fastcampus.eatgo.application;


import kr.co.fastcampus.eatgo.domain.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@AllArgsConstructor
@NoArgsConstructor
public class RestaurantService {

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private MenuItemRepository menuItemRepository;

    @Autowired
    private ReviewRepository reviewRepository;


    public List<Restaurant> getRestaurants() {
        List<Restaurant> restaurants = restaurantRepository.findAll();
        /*
            menuItems를 전부 불러와서 restaurants 애들이랑 menuItems를 초기화 시켜준다.
        */
        return restaurants;
    }

    public Restaurant getRestaurant(Long id){
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        List<MenuItem> menuItems = menuItemRepository.findAllByRestaurantId(id);
        List<Review> review = reviewRepository.findAllByRestaurantId(id);
        restaurant.setMenuItems(menuItems);
        restaurant.setReviews(review);
        return restaurant;
    }

    public List<MenuItem> getMenuItems() {
        return menuItemRepository.findAll();
    }

    public Restaurant addRestaurant(Restaurant restaurant) {
        Restaurant saved =  restaurantRepository.save(restaurant);
        return saved;
    }

    @Transactional
    public Restaurant updateRestaurant(Long id, String name, String address) {
        Restaurant restaurant = restaurantRepository.findById(id).orElse(null);
        restaurant.updateInformation(name, address);
        return restaurant;
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
