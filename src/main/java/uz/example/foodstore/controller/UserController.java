package uz.example.foodstore.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import uz.example.foodstore.dto.request.UserRegisterDto;
import uz.example.foodstore.dto.request.UserUpdateDto;
import uz.example.foodstore.dto.response.RoleDto;
import uz.example.foodstore.dto.response.UserDto;
import uz.example.foodstore.service.UserService;

@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;

    @PostMapping("/regis")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto>register(@RequestBody UserRegisterDto userRegisterDto){
        return ResponseEntity.ok(userService.save(userRegisterDto));
    }
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<Boolean> deleteById(@PathVariable Long id){
        return ResponseEntity.ok(userService.deleteById(id));
    }
    @PutMapping("/update")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto>updateById(@RequestBody UserUpdateDto UserUpdateDto){
        return ResponseEntity.ok(userService.updateById(UserUpdateDto));
    }
    @GetMapping("/find/by/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<UserDto>getById(@PathVariable String username) {
        return ResponseEntity.ok(userService.findUserDtoByUsername(username));

    }


}
