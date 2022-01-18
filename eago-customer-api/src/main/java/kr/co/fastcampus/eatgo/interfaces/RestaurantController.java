package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.application.RestaurantService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@CrossOrigin
@RestController
public class RestaurantController {
    @Autowired
    private RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public List<Restaurant> list(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        return restaurants;
    }
    @GetMapping("/restaurant/{id}")
    public Restaurant detail(@PathVariable("id") Long id){
        //repository가 2개 이상 관리 헤주고 2가지 정보를 리턴해주는곳 service
        Restaurant restaurant = restaurantService.getRestaurant(id);
        //restaurant.setMenuItems(menuItems);
        return restaurant;
    }

}
