package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
@Service
@NoArgsConstructor
@AllArgsConstructor
public class MenuItemService {

    @Autowired
    private MenuItemRepository menuItemRepository;


    public void bulkUpdate(Long restaurantId, List<MenuItem> menuItems) {
        menuItems.forEach(
                it ->{
                    update(restaurantId, it);
                }
        );
    }

    private void update(Long restaurantId, MenuItem it) {
        if(it.isDestroy()){
            //삭제
            // TODO: delete
            menuItemRepository.deleteById(it.getId());
            return;
        }
        it.setRestaurantId(restaurantId);
        menuItemRepository.save(it);
    }

    public List<MenuItem> getMenuItems(Long restaurantId) {
        return menuItemRepository.findAllByRestaurantId(restaurantId);
    }
}
