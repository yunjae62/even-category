package ex.evencategory.service;

import static org.assertj.core.api.Assertions.assertThat;

import ex.evencategory.TestDBConfig;
import ex.evencategory.domain.Category;
import ex.evencategory.repository.CategoryRepository;
import java.util.List;
import javax.sql.DataSource;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
class CategoryCommandServiceTest extends TestDBConfig {

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private CategoryCommandService categoryCommandService;

    @Autowired
    private DataSource dataSource;

    @Test
    @Transactional
    void createCategory() {
        // when
        Category shoes = categoryCommandService.createCategory("신발", "111");
        Category converse = categoryCommandService.createCategory("신발>컨버스", "111>100");
        Category slippers = categoryCommandService.createCategory("신발>슬리퍼", "111>101");

        // then
        assertThat(shoes.getName()).isEqualTo("신발");
        assertThat(shoes.getCode()).isEqualTo("111");

        assertThat(converse.getName()).isEqualTo("신발>컨버스");
        assertThat(converse.getCode()).isEqualTo("111>100");

        assertThat(slippers.getName()).isEqualTo("신발>슬리퍼");
        assertThat(slippers.getCode()).isEqualTo("111>101");
    }

    @Test
    @Transactional
    void updateCategoryName() {
        // when
        List<Category> categories = categoryCommandService.updateCategoryName("하의", "바지");

        // then
        assertThat(categories.get(0).getName()).isEqualTo("바지");
        assertThat(categories.get(0).getCode()).isEqualTo("101");

        assertThat(categories.get(1).getName()).isEqualTo("바지>청바지");
        assertThat(categories.get(1).getCode()).isEqualTo("101>100");

        assertThat(categories.get(2).getName()).isEqualTo("바지>츄리닝");
        assertThat(categories.get(2).getCode()).isEqualTo("101>101");
    }

    @Test
    @Transactional
    void moveCategoryCode() {
        // given
        Category tempCategory = Category.create("바지", "110");
        categoryRepository.save(tempCategory);

        // when
        List<Category> categories = categoryCommandService.moveCategoryCode("101", "110");

        // then
        assertThat(categories.get(0).getName()).isEqualTo("하의");
        assertThat(categories.get(0).getCode()).isEqualTo("110");

        assertThat(categories.get(1).getName()).isEqualTo("하의>청바지");
        assertThat(categories.get(1).getCode()).isEqualTo("110>100");

        assertThat(categories.get(2).getName()).isEqualTo("하의>츄리닝");
        assertThat(categories.get(2).getCode()).isEqualTo("110>101");
    }

    @Test
    @Transactional
    void updateCategoryByCode() {
        // when
        List<Category> categories = categoryCommandService.updateCategoryByCode("101", "110", "바지");

        // then
        assertThat(categories.get(0).getName()).isEqualTo("바지");
        assertThat(categories.get(0).getCode()).isEqualTo("110");

        assertThat(categories.get(1).getName()).isEqualTo("바지>청바지");
        assertThat(categories.get(1).getCode()).isEqualTo("110>100");

        assertThat(categories.get(2).getName()).isEqualTo("바지>츄리닝");
        assertThat(categories.get(2).getCode()).isEqualTo("110>101");
    }

    @Test
    @Transactional
    void deleteCategoryByCode() {
        // given
        categoryCommandService.createCategory("신발", "112");
        categoryCommandService.createCategory("신발>컨버스", "112>100");
        categoryCommandService.createCategory("신발>슬리퍼", "112>101");

        // when
        categoryCommandService.deleteCategoryByCode("112");
        List<Category> categoryList = categoryRepository.findAllSubByCodeLike("112");

        // then
        assertThat(categoryList).isEmpty();
    }
}