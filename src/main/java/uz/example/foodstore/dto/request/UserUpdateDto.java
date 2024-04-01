package uz.example.foodstore.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import uz.example.foodstore.entity.Role;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class UserUpdateDto {
    private Long id;
    private String name;
    private String username;
    private String password;
    private String condition;
    private List<Long> rolesId;
}
