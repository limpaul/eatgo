package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;
@ExtendWith(SpringExtension.class)
class ReviewServiceTest {

    private ReviewService reviewService;

    @MockBean
    private ReviewRepository reviewRepository;

    @BeforeEach
    public void init(){
        reviewService = new ReviewService(reviewRepository);
    }
    @Test
    public void getReviews(){
        Review mockReview = Review.builder().description("Cool!").build();
        when(reviewRepository.findAll()).thenReturn(Arrays.asList(mockReview));

        List<Review> reviews = reviewService.getReviews();
        assertEquals("Cool!", reviews.get(0).getDescription());
        verify(reviewRepository, times(1)).findAll();
    }

}