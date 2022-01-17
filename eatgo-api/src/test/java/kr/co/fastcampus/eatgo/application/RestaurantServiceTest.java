package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(SpringExtension.class) //단위테스트에 공통적으로 사용할 확장 테스트,.
class RestaurantServiceTest {

    /*@SpyBean(RestaurantService.class)
    private RestaurantService restaurantService;
    @SpyBean(RestaurantRepositoryImpl.class)
    private RestaurantRepository restaurantRepository;
    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;
*/
    @MockBean
    private RestaurantService restaurantService;
    @MockBean
    private RestaurantRepository restaurantRepository;
    @MockBean
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void init(){
        mockRestaurantRepository();
    }
    @Test
    public void getRestaurants(){
        List<Restaurant> restaurants = restaurantRepository.findAll();
        Assertions.assertEquals(1004L, restaurants.get(0).getId());
    }
    @Test
    public void getRestaurant(){
        Restaurant restaurant = restaurantService.getRestaurant(1004L);
        Assertions.assertEquals(1004L, restaurant.getId());
        MenuItem menuItem = restaurantService.getMenuItems().get(0);
        Assertions.assertEquals("Kimchi", menuItem.getName());
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
        Assertions.assertEquals(updated.getName(), "Sool zip");
        Assertions.assertEquals(updated.getAddress(), "Seoul");
    }
    private void mockRestaurantRepository(){
        List<Restaurant> restaurants = new ArrayList<>();
        Restaurant restaurant = new Restaurant(1004L, "BeRyong", "Busan");
        restaurant.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        restaurants.add(restaurant);
        Mockito.when(restaurantRepository.findAll()).thenReturn(restaurants);
        Mockito.when(restaurantRepository.findById(1004L)).thenReturn(Optional.of(restaurant));

        Mockito.when(restaurantService.getRestaurant(1004L)).thenReturn(restaurant);
        Mockito.when(restaurantService.getRestaurants()).thenReturn(restaurants);
        Mockito.when(restaurantService.getMenuItems()).thenReturn(Arrays.asList(new MenuItem("Kimchi")));

        Mockito.when(restaurantService.addRestaurant(Mockito.any())).thenReturn(restaurant);
        Mockito.when( restaurantService.updateRestaurant(Mockito.any(), Mockito.any(), Mockito.any()))
                .thenReturn(new Restaurant(1004L, "Sool zip", "Seoul"));
    }
}