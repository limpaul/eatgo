package kr.co.fastcampus.eatgo.application;

import kr.co.fastcampus.eatgo.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import java.util.*;
import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
class CategoryServiceTest {

    private CategoryService categoryService;
    @MockBean
    private CategoryRepository categoryRepository;

    @BeforeEach
    public void setup(){
        categoryService = new CategoryService(categoryRepository);
    }
    @Test
    public void getCategories(){
        when(categoryRepository.findAll())
                .thenReturn(Arrays.asList(Category.builder().name("Korean Food").build()));

        List<Category> categories = categoryRepository.findAll();
        Category category = categories.get(0);
        assertEquals(category.getName(), "Korean Food");
    }
    @Test
    public void addCategories(){
        Category category = categoryService.addCategory("Korean Food");
        verify(categoryRepository).save(any());
        assertEquals("Korean Food", category.getName());
    }
}