package ex.evencategory.repository;

import ex.evencategory.domain.RecCategory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecCategoryRepository extends JpaRepository<RecCategory, Long> {

}
