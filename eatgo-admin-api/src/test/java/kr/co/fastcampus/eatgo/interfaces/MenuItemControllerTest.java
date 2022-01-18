package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.MenuItemService;
import kr.co.fastcampus.eatgo.domain.MenuItem;
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
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
@WebMvcTest(MenuItemController.class)
class MenuItemControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private MenuItemService menuItemService;


    @Test
    public void bulkUpdate() throws Exception{
        mvc.perform(MockMvcRequestBuilders.patch("/restaurants/12/menuitems")
                .contentType(MediaType.APPLICATION_JSON)
                .content("[]"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andDo(MockMvcResultHandlers.print());

        Mockito.verify(menuItemService).bulkUpdate(Mockito.eq(12L), Mockito.any());
    }
}