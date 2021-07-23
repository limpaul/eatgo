package kr.co.restaurant.eatgo.application;

import kr.co.restaurant.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;

class RestaurantServiceTest {
    private RestaurantService restaurantService;
    @Mock
    private RestaurantRepository restaurantRepository;
    @Mock
    private MenuItemRepository menuItemRepository;


    @BeforeEach
    public void setup(){
       mockRestaurantRepository();
       mockMenuItemRepository();
    }
    public void mockRestaurantRepository(){
        MockitoAnnotations.openMocks(this);
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        given(restaurantRepository.findAll()).willReturn(restaurants);
    }
    public void mockMenuItemRepository(){
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        restaurant.addMenuItem(new MenuItem("Kimchi"));
        given(restaurantRepository.findById(1004L)).willReturn(restaurant);
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository);
    }
    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        assertEquals(1004L, restaurant.getId());
        MenuItem menuItem = restaurant.getMenuItems().get(0);
        assertEquals(menuItem.getName(), "Kimchi");
    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        assertEquals(1004L, restaurants.get(0).getId());
    }
}