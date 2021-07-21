package kr.co.restaurant.eatgo.domain;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;



class RestaurantTests {
    @Test
    public void creation(){
        Restaurant restaurant = new Restaurant("Bob zip");
        Assertions.assertEquals("Bob zip", restaurant.getName());
    }
}