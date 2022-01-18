package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.ReviewRepository;
import kr.co.fastcampus.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.*;
@Service
public class ReviewService {

    private ReviewRepository reviewRepository;

    @Autowired
    public ReviewService(ReviewRepository reviewRepository){
        this.reviewRepository = reviewRepository;
    }
    public Review addReview(Long restaurantId, Review review) {
        review.setRestaurantId(restaurantId);
        return reviewRepository.save(review);
    }

    public List<Review> getReviews() {
        return reviewRepository.findAll();
    }
}
