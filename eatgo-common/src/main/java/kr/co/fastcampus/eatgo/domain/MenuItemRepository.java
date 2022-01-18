package kr.co.fastcampus.eatgo.domain;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface MenuItemRepository extends CrudRepository<MenuItem, Long> {
    /*  select * from where restaurantId = ? 그래서 MenuItem에 restaurantId 변수가 필요.  */
    List<MenuItem> findAllByRestaurantId(Long restaurantId);

    List<MenuItem> findAll();

    void deleteById(Long id);
}
