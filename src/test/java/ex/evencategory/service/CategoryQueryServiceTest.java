package ex.evencategory.service;

import ex.evencategory.domain.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryQueryServiceTest {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Test
    void getCategoryById() {
        Category category = categoryQueryService.getCategoryById(1L);
        System.out.println("category = " + category);
    }

    @Test
    void getCategoryByCode() {
        Category category = categoryQueryService.getCategoryByCode("100");
        System.out.println("category = " + category);
    }

    @Test
    void getAllSubCategoryListByCode() {
        List<Category> categoryList = categoryQueryService.getAllSubCategoryListByCode("100");
        for (Category category : categoryList) {
            System.out.println("category = " + category);
        }
    }

    @Test
    void getOnlySubCategoryListByCode() {
        List<Category> categoryList = categoryQueryService.getOnlySubCategoryListByCode("100");
        for (Category category : categoryList) {
            System.out.println("category = " + category);
        }
    }
}