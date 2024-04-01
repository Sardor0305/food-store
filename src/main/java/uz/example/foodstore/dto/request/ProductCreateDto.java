package uz.example.foodstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ProductCreateDto {
    private String name;
    private Double amount;
    private String condition;
    private LocalDateTime keepingTime;
    private Long categoryId;
}
