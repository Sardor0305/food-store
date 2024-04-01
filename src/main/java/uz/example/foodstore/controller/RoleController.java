package uz.example.foodstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.example.foodstore.dto.request.ProductUpdateDto;
import uz.example.foodstore.dto.request.RoleCreateDto;
import uz.example.foodstore.dto.response.ProductDto;
import uz.example.foodstore.dto.response.RoleDto;
import uz.example.foodstore.service.RoleService;

import java.util.List;

@RestController
@RequestMapping("/role")
@RequiredArgsConstructor
public class RoleController{
    private final RoleService roleService;

    @PostMapping("/save")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleDto> save(@RequestBody RoleCreateDto roleCreateDto){
        return ResponseEntity.ok(roleService.save(roleCreateDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.deleteById(id));
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<RoleDto>updateById(@RequestBody  RoleDto roleDto){
        return ResponseEntity.ok(roleService.updateById(roleDto));
    }
    @GetMapping("/find/by/{id}")
    public ResponseEntity<RoleDto>getById(@PathVariable Long id){
        return ResponseEntity.ok(roleService.getRoleById(id));
    }
    @GetMapping("/find/all")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<List<RoleDto>>getAll(){
        return ResponseEntity.ok(roleService.allRole());
    }
}
