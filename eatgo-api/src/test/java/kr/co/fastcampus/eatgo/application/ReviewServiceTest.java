package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.Review;
import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

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
    public void addReview(){
        Review review = Review.builder()
                .name("Joker")
                .score(3)
                .description("mat-it-da")
                .build();
        reviewService.addReview(review);

        Mockito.verify(reviewRepository, Mockito.times(1)).save(Mockito.any());
    }
}