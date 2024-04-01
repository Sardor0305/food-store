package uz.example.foodstore.service.impl;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.ProductCreateDto;
import uz.example.foodstore.dto.request.ProductUpdateDto;
import uz.example.foodstore.dto.response.ProductDto;
import uz.example.foodstore.dto.response.UserDto;
import uz.example.foodstore.entity.Product;
import uz.example.foodstore.exeption.NotFoundException;
import uz.example.foodstore.repository.CategoryRepository;
import uz.example.foodstore.repository.ProductRepository;
import uz.example.foodstore.service.ProductService;
import uz.example.foodstore.utils.CacheName;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#id")
            }
    )
    @Override
    public ProductDto save(final ProductCreateDto productCreateDto) {
        return new ProductDto(productRepository.save(Product.builder()
                .name(productCreateDto.getName())
                .amount(productCreateDto.getAmount())
                .keepingTime(productCreateDto.getKeepingTime())
                .condition(productCreateDto.getCondition())
                .category(categoryRepository.findById(productCreateDto.getCategoryId()).orElseThrow(() -> new NotFoundException("category")))
                .build()));
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#id")
            }
    )
    public Boolean deleteById(final Long id) {
        findById(id);
        productRepository.deleteById(id);
        return true;
    }

    @Override
    @Caching(
            evict = {
                    @CacheEvict(value = CacheName.Fields.PRODUCT, allEntries = true),
                    @CacheEvict(value = CacheName.Fields.PRODUCT_BY_CATEGORY_ID,allEntries = true,condition = "#productUpdateDto.id")
            }
    )
    public ProductDto updateById(final ProductUpdateDto productUpdateDto) {
        final Product product = productRepository.findById(productUpdateDto.getId()).orElseThrow(() ->new NotFoundException("product"));
        product.setName(productUpdateDto.getName());
        product.setAmount(productUpdateDto.getAmount());
        product.setCondition(productUpdateDto.getCondition());
        product.setKeepingTime(productUpdateDto.getKeepingTime());
        productRepository.save(product);
        return new ProductDto(product);
    }

    @Override
    public ProductDto findById(final Long id) {
        return productRepository.findByProductId(id).orElseThrow(() -> new NotFoundException("product"));

    }

    @Override
    @Cacheable(CacheName.Fields.PRODUCT)
    public List<ProductDto> findAll() {

        return productRepository.findAllProduct().orElse(null);
    }

    @Override
    @Cacheable(CacheName.Fields.PRODUCT_BY_CATEGORY_ID)
    public List<ProductDto> findByCategoryId(Long categoryId) {
        System.out.println("findAll");
        return productRepository.findAllProductCategoryId(categoryId).orElse(null);
    }

    @Override
    public Long countByCategoryId(Long categoryId) {
        return productRepository.countByCategory(categoryId).orElse(0L);
    }
}
