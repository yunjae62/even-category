package ex.evencategory.service;

import ex.evencategory.domain.Category;
import ex.evencategory.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j(topic = "category-command-service")
@Service
@RequiredArgsConstructor
@Transactional
public class CategoryCommandService {

    private final CategoryRepository categoryRepository;

    /**
     * 카테고리 생성
     */
    public Category createCategory(String name, String code) {
        Category category = Category.create(name, code);
        return categoryRepository.save(category);
    }

    /**
     * 카테고리 이름 변경 (하위 카테고리까지 모두 변경)
     */
    public List<Category> updateCategoryName(String beforeName, String afterName) {
        List<Category> categoryList = categoryRepository.findAllSubByNameLike(beforeName);
        categoryList.forEach(category -> category.updateName(beforeName, afterName));

        return categoryRepository.saveAll(categoryList);
    }

    /**
     * 카테고리 코드 변경 (하위 카테고리까지 모두 변경)
     */
    public List<Category> moveCategoryCode(String beforeCode, String afterCode) {
        List<Category> categoryList = categoryRepository.findAllSubByCodeLike(beforeCode);
        categoryList.forEach(category -> category.updateCode(beforeCode, afterCode));

        return categoryRepository.saveAll(categoryList);
    }

    /**
     * 카테고리 코드로 코드와 이름 변경 (하위 카테고리까지 모두 변경)
     */
    public List<Category> updateCategoryByCode(String beforeCode, String afterCode, String afterName) {
        List<Category> categoryList = categoryRepository.findAllSubByCodeLike(beforeCode);
        categoryList.forEach(category -> category.updateByCode(beforeCode, afterCode, afterName));

        return categoryRepository.saveAll(categoryList);
    }

    /**
     * 카테고리 삭제
     */
    public void deleteCategoryByCode(String code) {
        List<Category> categoryList = categoryRepository.findAllSubByCodeLike(code);
        categoryRepository.deleteAll(categoryList);
    }
}
