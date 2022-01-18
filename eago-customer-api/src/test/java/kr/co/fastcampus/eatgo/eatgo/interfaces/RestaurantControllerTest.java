package kr.co.fastcampus.eatgo.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.Restaurant;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.interfaces.RestaurantController;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import java.util.*;

@WebMvcTest(RestaurantController.class)
class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RestaurantService restaurantService;

    @Test
    public void list() throws Exception {
        List<Restaurant> restaurants = new ArrayList<>();
        restaurants.add(new Restaurant(1004L, "Bob zip", "Seoul"));
        Mockito.when(restaurantService.getRestaurants()).thenReturn(restaurants);
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].information").value("Bob zip in Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].id").value(1004))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void detail() throws Exception{
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");

        MenuItem menuItem = MenuItem.builder().id(1004L).restaurantId(1L).name("Kimchi").build();
        restaurant.setMenuItems(Arrays.asList(menuItem));

        Review review = Review.builder().name("Bob zip").id(1004L).score(3).description("Great!").build();
        restaurant.setReviews(Arrays.asList(review));

        Mockito.when(restaurantService.getRestaurant(1004L)).thenReturn(restaurant);

        mvc.perform(MockMvcRequestBuilders.get("/restaurant/1004"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems[0:].name").value("Kimchi"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.reviews[0:].description").value("Great!"))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    public void updateRestaurant(){

    }
}
/*
* 의존성 주입을 하면 각각의 객체들의 강하게 연결되어있는 관계를 좀 더 유연하게 바꿀 수 있다.
* 객체를 직접 생성하고 관리해야한는 것을 spring이 해줘서 좀 더 business logic에 신경 쓸 수 있다.
* */