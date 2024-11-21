package ex.evencategory.service;

import ex.evencategory.domain.Category;
import ex.evencategory.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j(topic = "category-service")
@Service
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    /**
     * ID로 카테고리 조회
     */
    @Transactional(readOnly = true)
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "카테고리를 찾을 수 없습니다."));
    }

    /**
     * 코드로 카테고리 단일 조회
     */
    @Transactional(readOnly = true)
    public Category getCategoryByCode(String code) {
        return categoryRepository.findByCode(code)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "카테고리를 찾을 수 없습니다."));
    }

    /**
     * 코드로 루트 + 하위 카테고리 조회
     */
    @Transactional(readOnly = true)
    public List<Category> getAllSubCategoryListByCode(String code) {
        return categoryRepository.findAllSubByCodeLike(code);
    }

    /**
     * 코드로 하위 카테고리만 조회
     */
    @Transactional(readOnly = true)
    public List<Category> getOnlySubCategoryListByCode(String code) {
        return categoryRepository.findOnlySubByCodeLike(code);
    }
}
