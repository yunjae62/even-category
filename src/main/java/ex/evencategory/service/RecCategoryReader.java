package ex.evencategory.service;

import ex.evencategory.domain.RecCategory;
import ex.evencategory.repository.RecCategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Slf4j(topic = "rec-category-reader")
@Service
@RequiredArgsConstructor
public class RecCategoryReader {

    private final RecCategoryRepository recCategoryRepository;

    @Cacheable(cacheNames = "rec-category", key = "#id", cacheManager = "caffeineCacheManager")
    public RecCategory getCategoryById(Long id) {
        return recCategoryRepository.findById(id)
            .orElseThrow(() -> new HttpClientErrorException(HttpStatusCode.valueOf(404), "존재하지 않는 카테고리입니다."));
    }
}
