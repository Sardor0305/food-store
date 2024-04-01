package uz.example.foodstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.example.foodstore.dto.request.ProductCreateDto;
import uz.example.foodstore.dto.request.ProductUpdateDto;
import uz.example.foodstore.dto.response.ProductDto;
import uz.example.foodstore.service.ProductService;

import java.util.List;

@RestController
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {
    private final ProductService productService;
    @PostMapping("/save")
    public ResponseEntity<ProductDto> save(@RequestBody ProductCreateDto productCreateDto){
        return ResponseEntity.ok(productService.save(productCreateDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
       return ResponseEntity.ok(productService.deleteById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<ProductDto>updateById(@RequestBody ProductUpdateDto productUpdateDto){
        return ResponseEntity.ok(productService.updateById(productUpdateDto));
    }
    @GetMapping("/find/by/{id}")
    public ResponseEntity<ProductDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(productService.findById(id));
    }
    @GetMapping("/find/by/category/{id}")
    public ResponseEntity<List<ProductDto>>getByIdCategoryId(@PathVariable Long id){
        return ResponseEntity.ok(productService.findByCategoryId(id));
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<ProductDto>>getAll(){
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/find/count/{categoryId}")
    public ResponseEntity<Long>getByProductNumber(@PathVariable Long categoryId){
        return ResponseEntity.ok(productService.countByCategoryId(categoryId));
    }


}
