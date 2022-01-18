package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReviewService;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.junit.jupiter.api.Assertions.*;

@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    // review
    // http://localhost:8080/restaurants/{reviewId}/reviews name='??' score='??' description='??'
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReviewService reviewService;

    /*
    {"name":"Joker","score":3, "description":"mat-it-da"}
    */
    @Test
    public void create() throws Exception{
        Review review = Review.builder()
                .id(1234L).name("Joker").score(3).description("mat-it-da").build();
        Mockito.when(reviewService.addReview(Mockito.any(), Mockito.any())).thenReturn(review);
        mvc.perform(MockMvcRequestBuilders.post("/restaurants/1/reviews")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"name\":\"Joker\",\"score\":3, \"description\":\"mat-it-da\"}")
        ).andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(MockMvcResultMatchers.header().string("Location", "/restaurants/1/reviews/1234"))
                .andDo(MockMvcResultHandlers.print());



        Mockito.verify(reviewService).addReview(Mockito.any(), Mockito.any());
    }
}