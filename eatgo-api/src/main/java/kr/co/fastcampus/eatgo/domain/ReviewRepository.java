package kr.co.fastcampus.eatgo.domain;

import kr.co.fastcampus.eatgo.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
     List<Review> findAllByRestaurantId(Long restaurantId);
     List<Review> findAll();
     Review save(Review review);
}
