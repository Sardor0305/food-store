package uz.example.foodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.example.foodstore.dto.response.RoleDto;
import uz.example.foodstore.entity.Role;

import java.util.List;
import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Role,Long> {
    @Query("select new uz.example.foodstore.dto.response.RoleDto(r) from Role r where r.id =:id")
    Optional<RoleDto> getByRoleId(Long id);
    @Query("select new uz.example.foodstore.dto.response.RoleDto(r) from Role r")
    Optional<List<RoleDto>> getAllRole();
    boolean existsByValue(String value);
}
