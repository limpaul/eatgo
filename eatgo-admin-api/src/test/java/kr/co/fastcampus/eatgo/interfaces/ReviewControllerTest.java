package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReviewService;
import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.mockito.Mockito.*;
import static org.hamcrest.Matchers.*;
import java.util.*;
@WebMvcTest(ReviewController.class)
class ReviewControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private ReviewService reviewService;

   @Test
    public void list() throws Exception{
       List<Review> reviews = new ArrayList<>();
       reviews.add(Review.builder().id(1L).score(3).description("Cool!").build());

       when(reviewService.getReviews()).thenReturn(reviews);
       mvc.perform(get("/reviews"))
               .andExpect(status().isOk())
              // .andExpect(content().string(containsString("Cool!")))
               .andDo(print());
   }

}