package kr.co.fastcampus.eatgo.domain;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

public class CategoryTest {

    @Test
    public void creation(){
        Category category = Category.builder().name("Korean Food").build();

        assertEquals("Korean Food", category.getName());

    }
}
