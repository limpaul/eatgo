package kr.co.fastcampus.eatgo.interfaces;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

//@SpringBootTest // 모든 빈을 올리는 통합테스트, 무겁다.

@WebMvcTest(RestaurantController.class) // controller를 테스트
@AutoConfigureWebMvc //MVC와 관련된 Bean을 올린다
class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;
    @Test
    public void list() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/restaurant"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Bob zip")));
    }
    @Test
    public void list_1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/restaurant"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$..name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..information").value("Bob zip in Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$..id").value(1004))
                .andDo(MockMvcResultHandlers.print());
    }
    
}