package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import kr.co.fastcampus.eatgo.domain.MenuItemRepositoryImpl;
import kr.co.fastcampus.eatgo.domain.RestaurantRepository;
import kr.co.fastcampus.eatgo.domain.RestaurantRepositoryImpl;
import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureWebMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.SpyBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;


//@SpringBootTest // 모든 빈을 올리는 통합테스트, 무겁다.
@WebMvcTest(RestaurantController.class) // controller를 테스트
@AutoConfigureWebMvc //MVC와 관련된 Bean을 올린다
class RestaurantControllerTest {
    @Autowired
    private MockMvc mvc;

    // 같은 방식으로 작동하는 객체를 관리할 수 있다.
    @SpyBean(RestaurantRepositoryImpl.class) // controller에 원하는 bean을 등록해줄 수 있다.
    private RestaurantRepository restaurantRepository;

    @SpyBean(MenuItemRepositoryImpl.class)
    private MenuItemRepository menuItemRepository;
    @Test
    public void list() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.content().string(CoreMatchers.containsString("Bob zip")))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void list_1() throws Exception {
        mvc.perform(MockMvcRequestBuilders.get("/restaurants"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]..name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]..information").value("Bob zip in Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0]..id").value(1004))
                .andDo(MockMvcResultHandlers.print());
    }
    @Test
    public void detail() throws Exception{
        mvc.perform(MockMvcRequestBuilders.get("/restaurant/1004"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(1004))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Bob zip"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Seoul"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.menuItems[0:].name").value("Kimchi"))
                .andDo(MockMvcResultHandlers.print());
        mvc.perform(MockMvcRequestBuilders.get("/restaurant/2020"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.id").value(2020))
                .andExpect(MockMvcResultMatchers.jsonPath("$.name").value("Cyber Food"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.address").value("Seoul"))
                .andDo(MockMvcResultHandlers.print());

    }

}
/*
* 의존성 주입을 하면 각각의 객체들의 강하게 연결되어있는 관계를 좀 더 유연하게 바꿀 수 있다.
* 객체를 직접 생성하고 관리해야한는 것을 spring이 해줘서 좀 더 business logic에 신경 쓸 수 있다.
* */