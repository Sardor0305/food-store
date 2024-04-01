package uz.example.foodstore.service;

import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.ProductCreateDto;
import uz.example.foodstore.dto.request.ProductUpdateDto;
import uz.example.foodstore.dto.response.ProductDto;

import java.util.List;
@Service
public interface ProductService {
    ProductDto save(final ProductCreateDto productCreateDto);
    Boolean deleteById(final Long id);
    ProductDto updateById(final ProductUpdateDto productUpdateDto);
    ProductDto findById(final Long id);
    List<ProductDto> findAll();

    List<ProductDto> findByCategoryId(final Long categoryId);

    Long countByCategoryId(final Long categoryId);
}
