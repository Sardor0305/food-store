package uz.example.foodstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.CategoryCreateDto;
import uz.example.foodstore.dto.request.CategoryUpdateDto;
import uz.example.foodstore.dto.response.CategoryDto;
import uz.example.foodstore.entity.Category;
import uz.example.foodstore.exeption.NotFoundException;
import uz.example.foodstore.repository.CategoryRepository;
import uz.example.foodstore.service.CategoryService;
import uz.example.foodstore.utils.CacheName;

import java.util.List;
@Service
@RequiredArgsConstructor
public class CategoryServiceDto implements CategoryService {
    private final CategoryRepository categoryRepository;
    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#id")
            }
    )
    public CategoryDto save(final CategoryCreateDto categoryCreateDto) {

        return new CategoryDto(categoryRepository.save(Category.builder()
                        .name(categoryCreateDto.getName())
                        .type(categoryCreateDto.getType())
                        .condition(categoryCreateDto.getCondition())
                .build()));
    }
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#id")
            }
    )
    @Override
    public Boolean deleteById(final Long id) {
        categoryRepository.findByCategoryId(id).orElseThrow(()->new NotFoundException("category"));
        categoryRepository.deleteById(id);
        return true;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#categoryUpdateDto.id")
            }
    )
    public CategoryDto updateById(final CategoryUpdateDto categoryUpdateDto) {
       final CategoryDto category = categoryRepository.findByCategoryId(categoryUpdateDto.getId()).orElseThrow(() -> new NotFoundException("category"));
       category.setName(categoryUpdateDto.getName());
       category.setType(categoryUpdateDto.getType());
       category.setCondition(categoryUpdateDto.getCondition());

       return category;
    }

    @Override
    public CategoryDto findById(final Long id) {
        return categoryRepository.findByCategoryId(id).orElseThrow(()->new NotFoundException("category"));
    }

    @Override
    public List<CategoryDto> findAllCategory() {
        return categoryRepository.findAllCategory().orElse(null);
    }
}
