package ex.evencategory.domain;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.Table;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@Entity
@ToString
@Table(name = "tb_rec_category", indexes = @Index(name = "idx_rec_category_parent_id", columnList = "parent_id"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class RecCategory {

    public static final Long ROOT_ID = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Long parentId;

    public static RecCategory create(String name, Long parentId) {
        RecCategory category = new RecCategory();

        category.name = name;
        category.parentId = parentId;

        return category;
    }
}
