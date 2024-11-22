package ex.evencategory.service;

import ex.evencategory.domain.Category;
import ex.evencategory.repository.CategoryRepository;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j(topic = "category-query-service")
@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class CategoryQueryService {

    private final CategoryRepository categoryRepository;

    /**
     * ID로 카테고리 조회
     */
    public Category getCategoryById(Long id) {
        return categoryRepository.findById(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "카테고리를 찾을 수 없습니다."));
    }

    /**
     * 코드로 카테고리 단일 조회
     */
    public Category getCategoryByCode(String code) {
        return categoryRepository.findByCode(code)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "카테고리를 찾을 수 없습니다."));
    }

    /**
     * 코드로 루트 + 하위 카테고리 조회
     */
    @Cacheable(value = "category::allsub", key = "#code")
    public List<Category> getAllSubCategoryListByCode(String code) {
        return categoryRepository.findAllSubByCodeLike(code);
    }

    /**
     * 코드로 하위 카테고리만 조회
     */
    @Cacheable(value = "category::onlysub", key = "#code")
    public List<Category> getOnlySubCategoryListByCode(String code) {
        return categoryRepository.findOnlySubByCodeLike(code);
    }
}
