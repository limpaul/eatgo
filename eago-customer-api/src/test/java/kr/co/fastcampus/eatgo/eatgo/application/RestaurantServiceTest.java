package kr.co.fastcampus.eatgo.eatgo.application;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import java.util.*;

@ExtendWith(SpringExtension.class) //단위테스트에 공통적으로 사용할 확장 테스트,.
class RestaurantServiceTest {

    private RestaurantService restaurantService;
    @MockBean
    private RestaurantRepository restaurantRepository;
    @MockBean
    private MenuItemRepository menuItemRepository;
    @MockBean
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void init(){
        mockRestaurantRepository();
        mockReviewRepository();
        restaurantService = new RestaurantService(restaurantRepository, menuItemRepository, reviewRepository);
    }
    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);

        verify(menuItemRepository).findAllByRestaurantId(eq(1004L));
        verify(reviewRepository).findAllByRestaurantId(eq(1004L));

        assertEquals(1004L, restaurant.getId());
        assertEquals("Kimchi", restaurant.getMenuItems().get(0).getName());
        assertEquals("BeRyong", restaurant.getReviews().get(0).getName());
        assertEquals(1, restaurant.getReviews().get(0).getScore());
        assertEquals("bad", restaurant.getReviews().get(0).getDescription());

    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantService.getRestaurants();
        Assertions.assertEquals(1004L, restaurants.get(0).getId());
    }
    @Test
    public void addRestaurant(){
        Restaurant restaurant = new Restaurant( "BeRyong", "Busan");
        Restaurant created =  restaurantService.addRestaurant(restaurant);
        Assertions.assertEquals(created.getId(), 1004L);
    }
    @Test
    public void updateRestaurant(){
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Busan");
        Mockito.when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        Restaurant updated = restaurantService.updateRestaurant(1004L, "Sool Zip", "Seoul");
        Assertions.assertEquals(updated.getName(), "Sool Zip");
        Assertions.assertEquals(updated.getAddress(), "Seoul");
    }
    private void mockRestaurantRepository(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "BeRyong", "Busan");
        restaurant.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        restaurants.add(restaurant);
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurants);
        Mockito.when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));
        Mockito.when(restaurantRepository.save(Mockito.any())).thenReturn(restaurant);
        Mockito.when(menuItemRepository.findAllByRestaurantId(Mockito.any())).thenReturn(Arrays.asList(new MenuItem("Kimchi")));
        Mockito.when(menuItemRepository.findAll()).thenReturn(Arrays.asList(new MenuItem("Kimchi")));
    }
    private void mockReviewRepository(){
        List<Review> reviews = new ArrayList<>();
        reviews.add(Review.builder()
                .name("BeRyong")
                .score(1)
                .description("bad")
                .build());
        when(reviewRepository.findAllByRestaurantId(any())).thenReturn(reviews);
        when(reviewRepository.findAll()).thenReturn(reviews);
    }
}