package uz.example.foodstore.repository;

import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.Repository;
import org.springframework.data.repository.query.Param;
import uz.example.foodstore.dto.response.ProductDto;
import uz.example.foodstore.entity.Product;

import java.util.List;
import java.util.Optional;

public interface ProductRepository extends JpaRepository<Product,Long> {
    @Query("select new uz.example.foodstore.dto.response.ProductDto(p) from Product p where p.id=:id")
    Optional<ProductDto>findByProductId(Long id);
    @Query("select new uz.example.foodstore.dto.response.ProductDto(p) from Product p")
    Optional<List<ProductDto>>findAllProduct();
    @Query("select new uz.example.foodstore.dto.response.ProductDto(p) from Product p where p.category.id =:categoryId")
    Optional<List<ProductDto>>findAllProductCategoryId(Long categoryId);
    @Query(nativeQuery = true , value = "select count(*) from product where category_id =:categoryId")
    Optional<Long>countByCategory(@Param("categoryId") Long categoryId);


}
