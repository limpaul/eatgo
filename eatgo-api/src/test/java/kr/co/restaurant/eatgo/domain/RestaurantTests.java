package kr.co.restaurant.eatgo.domain;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;



class RestaurantTests {
    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant(1004L,"Bob zip", "Seoul");
        assertEquals("Bob zip", restaurant.getName());
        assertEquals("Seoul", restaurant.getAddress());
        assertEquals(1004L, restaurant.getId());
    }
    @Test
    public void information(){
        Restaurant restaurant = new Restaurant(1004L, "Bob zip", "Seoul");
        assertEquals("Bob zip in Seoul", restaurant.getInformation());
    }

}