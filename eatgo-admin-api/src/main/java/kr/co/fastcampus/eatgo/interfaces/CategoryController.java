package kr.co.fastcampus.eatgo.interfaces;

import kr.co.fastcampus.eatgo.application.CategoryService;
import kr.co.fastcampus.eatgo.application.RegionService;
import kr.co.fastcampus.eatgo.domain.Category;
import kr.co.fastcampus.eatgo.domain.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.*;
@RestController
public class CategoryController {
    @Autowired
    private CategoryService cateGoryService;
    @GetMapping("/categories")
    public List<Category> list(){
        List<Category> categories = cateGoryService.getCategories();
        return categories;
    }

    @PostMapping("/categories")
    public ResponseEntity<?> create(@RequestBody Category resource) throws URISyntaxException {
        // TODO: 지역 생성
        cateGoryService.addCategory(resource.getName());
        String url = "/categories/"+resource.getId();
        return ResponseEntity.created(new URI(url)).body("{}");
    }
}
