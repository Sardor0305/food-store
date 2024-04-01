package uz.example.foodstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import uz.example.foodstore.dto.request.CategoryCreateDto;
import uz.example.foodstore.dto.request.CategoryUpdateDto;
import uz.example.foodstore.dto.request.ProductUpdateDto;
import uz.example.foodstore.dto.response.CategoryDto;
import uz.example.foodstore.dto.response.ProductDto;
import uz.example.foodstore.service.CategoryService;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @PostMapping("/save")
    public ResponseEntity<CategoryDto> save(CategoryCreateDto categoryCreateDto){
       return ResponseEntity.ok(categoryService.save(categoryCreateDto));
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.deleteById(id));
    }
    @PutMapping("/update")
    public ResponseEntity<CategoryDto>updateById(@RequestBody CategoryUpdateDto categoryUpdateDto){
        return ResponseEntity.ok(categoryService.updateById(categoryUpdateDto));
    }
    @GetMapping("/find/by/{id}")
    public ResponseEntity<CategoryDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.findById(id));
    }
    @GetMapping("/find/all")
    public ResponseEntity<List<CategoryDto>>getAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }


}
