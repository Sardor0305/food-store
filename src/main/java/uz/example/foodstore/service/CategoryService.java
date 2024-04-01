package uz.example.foodstore.service;

import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.CategoryCreateDto;
import uz.example.foodstore.dto.request.CategoryUpdateDto;
import uz.example.foodstore.dto.response.CategoryDto;

import java.util.List;

@Service
public interface CategoryService {

    CategoryDto save(final CategoryCreateDto categoryCreateDto);

    Boolean deleteById(final Long id);

    CategoryDto updateById(final CategoryUpdateDto categoryUpdateDto);

    CategoryDto findById(final Long id);
    List<CategoryDto> findAllCategory();

}
