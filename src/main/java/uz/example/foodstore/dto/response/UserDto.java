package uz.example.foodstore.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.security.core.userdetails.UserDetails;
import uz.example.foodstore.entity.Role;
import uz.example.foodstore.entity.User;

import java.io.Serializable;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class UserDto implements Serializable {
    private Long id;
    private String name;
    private String username;
    private String condition;
    private List<Role> roles;

    public UserDto(final User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.username = user.getUsername();
        this.condition = user.getCondition();
        this.roles = user.getRoles();
    }
}
