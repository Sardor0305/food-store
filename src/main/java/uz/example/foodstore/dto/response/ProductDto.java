package uz.example.foodstore.dto.response;

import jakarta.persistence.ManyToOne;
import lombok.*;
import uz.example.foodstore.entity.Category;
import uz.example.foodstore.entity.Product;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class ProductDto implements Serializable {
    private Long id;
    private String name;
    private Double amount;
    private String condition;
    private LocalDateTime keepingTime;
    private CategoryDto category;

    public ProductDto(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.amount =product.getAmount();
        this.condition = product.getCondition();
        this.keepingTime = product.getKeepingTime();
        this.category = new CategoryDto(product.getCategory());
    }
}
