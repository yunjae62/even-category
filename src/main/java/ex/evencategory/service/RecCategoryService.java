package ex.evencategory.service;

import ex.evencategory.domain.RecCategory;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Slf4j(topic = "rec-category-service")
@Service
@RequiredArgsConstructor
public class RecCategoryService {

    private final RecCategoryReader recCategoryReader;

    public List<RecCategory> getAllSuperCategoryList(Long id) {

        Deque<RecCategory> categoryQueue = new ArrayDeque<>();

        RecCategory nowCategory = recCategoryReader.getCategoryById(id);
        categoryQueue.add(nowCategory);

        while (!nowCategory.getParentId().equals(RecCategory.ROOT_ID)) {
            nowCategory = recCategoryReader.getCategoryById(nowCategory.getParentId());
            categoryQueue.addFirst(nowCategory);
        }

        return categoryQueue.stream().toList();
    }
}
