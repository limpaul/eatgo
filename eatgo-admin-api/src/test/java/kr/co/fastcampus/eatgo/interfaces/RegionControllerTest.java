package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.RegionService;
import kr.co.fastcampus.eatgo.domain.Region;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(RegionController.class)
class RegionControllerTest {
    @Autowired
    private MockMvc mvc;

    @MockBean
    private RegionService regionService;

    @Test
    public void list() throws Exception{
        List<Region> regions = new ArrayList<>();
        regions.add(Region.builder().name("Seoul").build());
        when(regionService.getRegions()).thenReturn(regions);
        mvc.perform(get("/regions"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("Seoul")))
                .andDo(print());
    }
    @Test
    public void create() throws Exception{
        // {"name":"Seoul"}
        Region region = Region.builder().name("Seoul").build();
        when(regionService.addRegion("Seoul")).thenReturn(region);
        mvc.perform(post("/regions")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Seoul\"}")
        )
                .andExpect(status().isCreated())
                .andExpect(content().string("{}"))
                .andDo(print());

        verify(regionService).addRegion(any());
    }
}