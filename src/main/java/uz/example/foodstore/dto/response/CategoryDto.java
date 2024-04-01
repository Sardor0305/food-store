package uz.example.foodstore.dto.response;

import lombok.*;
import uz.example.foodstore.entity.Category;

import java.io.Serializable;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CategoryDto implements Serializable {
    private Long id;
    private String name;
    private String type;
    private String condition;

    public CategoryDto(final Category category) {
        this.id = category.getId();
        this.name = category.getName();
        this.type = category.getType();
        this.condition = category.getCondition();

    }
}
