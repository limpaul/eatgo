package kr.co.restaurant.eatgo.domain;

import org.junit.jupiter.api.Test;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

class RestaurantRepositoryImpleTest {
    @Test
    public void save(){
        RestaurantRepository repository = new RestaurantRepositoryImple();
        int oldCount = repository.findAll().size();
        Restaurant restaurant = new Restaurant("BeRyong", "Seoul");
        repository.save(restaurant);
        assertThat(restaurant.getId(), is(1234L));
        int newCount = repository.findAll().size();
        assertEquals(1, newCount - oldCount);
    }
}