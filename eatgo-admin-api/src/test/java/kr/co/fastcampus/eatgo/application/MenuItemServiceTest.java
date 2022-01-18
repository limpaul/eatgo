package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.MenuItem;
import kr.co.fastcampus.eatgo.domain.MenuItemRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.mockito.Mockito.*;
import java.util.*;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(SpringExtension.class)
class MenuItemServiceTest {
    private MenuItemService menuItemService;
    @MockBean
    private MenuItemRepository menuItemRepository;

    @BeforeEach
    public void init(){
        menuItemService = new MenuItemService(menuItemRepository);
    }


    @Test
    public void getMenuItems(){
        List<MenuItem> mockMenuItems = new ArrayList<>();
        MenuItem mockMenuItem = MenuItem.builder().name("Kimchi").build();
        mockMenuItems.add(mockMenuItem);
        when(menuItemRepository.findAllByRestaurantId(any())).thenReturn(mockMenuItems);
        List<MenuItem>  menuItems = menuItemService.getMenuItems(1004L);
        MenuItem menuItem = menuItems.get(0);
        assertEquals("Kimchi", menuItem.getName());
    }

    @Test
    public void bulkupdate(){
        List<MenuItem> menuItems = new ArrayList<MenuItem>();
        menuItems.add(new MenuItem("Kimchi")); // Create
        menuItems.add(MenuItem.builder().id(12L).name("Gukbob").build()); // Update
        menuItems.add(MenuItem.builder().id(1004L).destroy(true).build()); // delete
        menuItemService.bulkUpdate(1L, menuItems);
        Mockito.verify(menuItemRepository, Mockito.times(2)).save(Mockito.any());
        Mockito.verify(menuItemRepository, Mockito.times(1)).deleteById(Mockito.eq(1004L));

    }
}