package uz.example.foodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.example.foodstore.dto.response.UserDto;
import uz.example.foodstore.entity.User;

import java.util.Optional;
@Repository
public interface UserRepository extends JpaRepository<User,Long> {

    @Query("select new uz.example.foodstore.dto.response.UserDto(u) from User u where u.username =:username")
    Optional<UserDto>findUserByUsername(String username);
    Optional<User>findByUsernameAndPassword(String username,String password);
    @Query("select u from User u where u.username =:username")
    Optional<User> findByUsername(String username);

    boolean existsByUsername(String username);
}
