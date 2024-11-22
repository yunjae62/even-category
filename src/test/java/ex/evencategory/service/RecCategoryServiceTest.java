package ex.evencategory.service;

import static org.assertj.core.api.Assertions.assertThat;

import ex.evencategory.TestDBConfig;
import ex.evencategory.domain.RecCategory;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class RecCategoryServiceTest extends TestDBConfig {

    @Autowired
    private RecCategoryService recCategoryService;

    @Test
    void getAllSuperCategoryList() {
        // when
        List<RecCategory> categoryList = recCategoryService.getAllSuperCategoryList(10L);

        // then
        assertThat(categoryList).isNotNull();
        assertThat(categoryList.size()).isEqualTo(4);

        assertThat(categoryList.get(0).getName()).isEqualTo("뷰티");
        assertThat(categoryList.get(0).getCode()).isEqualTo("102");

        assertThat(categoryList.get(1).getName()).isEqualTo("클렌징");
        assertThat(categoryList.get(1).getCode()).isEqualTo("100");

        assertThat(categoryList.get(2).getName()).isEqualTo("페이셜클렌징");
        assertThat(categoryList.get(2).getCode()).isEqualTo("100");

        assertThat(categoryList.get(3).getName()).isEqualTo("클렌징폼");
        assertThat(categoryList.get(3).getCode()).isEqualTo("100");
    }
}