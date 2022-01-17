package kr.co.fastcampus.eatgo.domain;

import kr.co.fastcampus.eatgo.domain.Review;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ReviewRepository extends CrudRepository<Review, Long> {
     Review save(Review review);
}
