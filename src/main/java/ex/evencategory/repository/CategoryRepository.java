package ex.evencategory.repository;

import ex.evencategory.domain.Category;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface CategoryRepository extends JpaRepository<Category, Long> {

    Optional<Category> findByCode(String code);

    @Query("SELECT c FROM Category c WHERE c.code LIKE :code%")
    List<Category> findAllSubByCodeLike(String code);

    @Query("SELECT c FROM Category c WHERE c.code LIKE CONCAT(:code, '>%')")
    List<Category> findOnlySubByCodeLike(String code);

    @Query("SELECT c FROM Category c WHERE c.name LIKE :name%")
    List<Category> findAllSubByNameLike(String name);

    @Query("SELECT c FROM Category c WHERE c.name LIKE CONCAT(:name, '>%')")
    List<Category> findOnlySubByNameLike(String name);

    boolean existsByNameOrCode(String name, String code);
}
