package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.ReviewService;
import kr.co.fastcampus.eatgo.domain.Review;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class ReviewController {
    @Autowired
    private ReviewService reviewService;

    @GetMapping("/reviews")
    public List<Review> reviews(){
        List<Review> reviews = new ArrayList<>();
                //reviewService.getReviews();
        return reviews;
    }

    @PostMapping("/restaurants/{restaurantId}/reviews")
    public ResponseEntity<?> create(
            @PathVariable("restaurantId") Long id,
            @RequestBody Review resource
    ) throws URISyntaxException {
        Review review = reviewService.addReview(id, resource);

        String url = "/restaurants/"+id+
                "/reviews/"+ review.getId();

        return ResponseEntity.created(new URI(url))
                .body("{}");
    }
}
