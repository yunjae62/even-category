package ex.evencategory.service;

import ex.evencategory.domain.Category;
import ex.evencategory.repository.CategoryRepository;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CategoryCommandServiceTest {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryCommandService categoryCommandService;

    @Test
    @Transactional
    void createCategory() {
        Category shoes = categoryCommandService.createCategory("신발", "102");
        Category converse = categoryCommandService.createCategory("신발>컨버스", "102>100");
        Category slippers = categoryCommandService.createCategory("신발>슬리퍼", "102>101");

        System.out.println(shoes);
        System.out.println(converse);
        System.out.println(slippers);
    }

    @Test
    @Transactional
    void updateCategoryName() {
        List<Category> categories = categoryCommandService.updateCategoryName("상의", "윗옷");

        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    @Transactional
    void moveCategoryCode() {
        Category tempCategory = Category.create("바지", "102");
        categoryRepository.save(tempCategory);

        List<Category> categories = categoryCommandService.moveCategoryCode("101", "102");

        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    @Transactional
    void updateCategoryByCode() {
        List<Category> categories = categoryCommandService.updateCategoryByCode("101", "102", "바지");

        for (Category category : categories) {
            System.out.println(category);
        }
    }

    @Test
    @Transactional
    void deleteCategoryByCode() {
        categoryCommandService.createCategory("신발", "102");
        categoryCommandService.createCategory("신발>컨버스", "102>100");
        categoryCommandService.createCategory("신발>슬리퍼", "102>101");

        categoryCommandService.deleteCategoryByCode("102");
    }
}