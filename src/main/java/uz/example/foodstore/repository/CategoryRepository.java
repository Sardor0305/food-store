package uz.example.foodstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import uz.example.foodstore.dto.response.CategoryDto;
import uz.example.foodstore.entity.Category;

import java.util.List;
import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category,Long> {
    @Query("select new uz.example.foodstore.dto.response.CategoryDto(c) from Category c where c.id = :id")
    Optional<CategoryDto> findByCategoryId(Long id);
    @Query("select new uz.example.foodstore.dto.response.CategoryDto(c) from Category c")
    Optional<List<CategoryDto>> findAllCategory();

}
