package ex.evencategory.service;

import static org.assertj.core.api.Assertions.assertThat;

import ex.evencategory.TestDBConfig;
import ex.evencategory.domain.Category;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CategoryQueryServiceTest extends TestDBConfig {

    @Autowired
    private CategoryQueryService categoryQueryService;

    @Test
    void getCategoryById() {
        // when
        Category category = categoryQueryService.getCategoryById(1L);

        // then
        assertThat(category).isNotNull();
        assertThat(category.getName()).isEqualTo("상의");
        assertThat(category.getCode()).isEqualTo("100");
    }

    @Test
    void getCategoryByCode() {
        // when
        Category category = categoryQueryService.getCategoryByCode("100");

        // then
        assertThat(category).isNotNull();
        assertThat(category.getName()).isEqualTo("상의");
        assertThat(category.getCode()).isEqualTo("100");
    }

    @Test
    void getAllSubCategoryListByCode() {
        // when
        List<Category> categoryList = categoryQueryService.getAllSubCategoryListByCode("100");

        // then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(4);

        assertThat(categoryList.get(0).getName()).isEqualTo("상의");
        assertThat(categoryList.get(0).getCode()).isEqualTo("100");

        assertThat(categoryList.get(1).getName()).isEqualTo("상의>후드티");
        assertThat(categoryList.get(1).getCode()).isEqualTo("100>100");

        assertThat(categoryList.get(2).getName()).isEqualTo("상의>반팔");
        assertThat(categoryList.get(2).getCode()).isEqualTo("100>101");

        assertThat(categoryList.get(3).getName()).isEqualTo("상의>맨투맨");
        assertThat(categoryList.get(3).getCode()).isEqualTo("100>102");
    }

    @Test
    void getOnlySubCategoryListByCode() {
        // when
        List<Category> categoryList = categoryQueryService.getOnlySubCategoryListByCode("100");

        // then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(3);

        assertThat(categoryList.get(0).getName()).isEqualTo("상의>후드티");
        assertThat(categoryList.get(0).getCode()).isEqualTo("100>100");

        assertThat(categoryList.get(1).getName()).isEqualTo("상의>반팔");
        assertThat(categoryList.get(1).getCode()).isEqualTo("100>101");

        assertThat(categoryList.get(2).getName()).isEqualTo("상의>맨투맨");
        assertThat(categoryList.get(2).getCode()).isEqualTo("100>102");
    }
}