package uz.example.foodstore.dto.response;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import uz.example.foodstore.entity.Role;

import java.io.Serializable;

@Getter
@Setter
@ToString
public class RoleDto implements Serializable {
    private Long id;
    private String value;

    public RoleDto(final Role role) {
        this.id = role.getId();
        this.value = role.getValue();
    }
}
