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
@Table(name = "tb_category", indexes = @Index(name = "idx_category_code", columnList = "code"))
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Category {

    public static final String CATEGORY_DELIMITER = ">";

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, unique = true)
    private String code;

    public static Category create(String name, String code) {
        Category category = new Category();

        category.name = name;
        category.code = code;

        return category;
    }

    public void updateName(String beforeName, String afterName) {
        this.name = this.name.replaceFirst(beforeName, afterName);
    }

    public void updateCode(String beforeCode, String afterCode) {
        this.code = this.code.replaceFirst(beforeCode, afterCode);
    }

    public void updateByCode(String beforeCode, String afterCode, String afterName) {
        updateCode(beforeCode, afterCode);
        this.name = createAfterName(beforeCode, afterName);
    }

    private String createAfterName(String beforeCode, String afterName) {
        StringBuilder sb = new StringBuilder(afterName);

        int noChangeIndex = beforeCode.split(CATEGORY_DELIMITER).length;
        String[] names = this.name.split(CATEGORY_DELIMITER);

        for (int i = noChangeIndex; i < names.length; i++) {
            sb.append(CATEGORY_DELIMITER).append(names[i]);
        }

        return sb.toString();
    }
}
