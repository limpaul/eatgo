package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RestaurantService;
import kr.co.fastcampus.eatgo.domain.*;
import org.assertj.core.util.Lists;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.mock.mockito.SpyBean;
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
        restaurant.setMenuItems(Arrays.asList(new MenuItem("Kimchi")));
        Mockito.when(restaurantService.getRestaurant(1004L)).thenReturn(restaurant);

        mvc.perform(MockMvcRequestBuilders.get("/restaurant/1004"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems[0:].name").value("Kimchi"))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void create() throws Exception{
        Restaurant restaurant = new Restaurant(1L, "BeRyong", "Seoul");
        Mockito.when(restaurantService.addRestaurant(Mockito.any())).thenReturn(restaurant);

        mvc.perform(MockMvcRequestBuilders.post("/restaurants")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\": \"BeRyong\", \"address\":\"Seoul\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("location", "/restaurants/1"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("BeRyong"))
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(restaurantService).addRestaurant(Mockito.any());
    }

    @Test
    public void update() throws Exception{
        // {"name":"JOKER Bar", "address":"Busan"}
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/1004")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"JOKER Bar\", \"address\":\"Busan\"}")
                )
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(restaurantService).updateRestaurant(1004L, "JOKER Bar", "Busan");
    }
    @Test
    public void updateRestaurant(){

    }
}
/*
* 의존성 주입을 하면 각각의 객체들의 강하게 연결되어있는 관계를 좀 더 유연하게 바꿀 수 있다.
* 객체를 직접 생성하고 관리해야한는 것을 spring이 해줘서 좀 더 business logic에 신경 쓸 수 있다.
* */