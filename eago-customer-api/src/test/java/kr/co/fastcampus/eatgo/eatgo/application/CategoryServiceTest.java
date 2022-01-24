package kr.co.fastcampus.eatgo.eatgo.application;

import kr.co.fastcampus.eatgo.application.CategoryService;
import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

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
}