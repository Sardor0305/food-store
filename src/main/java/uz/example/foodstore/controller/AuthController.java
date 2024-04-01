package uz.example.foodstore.controller;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import uz.example.foodstore.dto.request.UserLonginDto;
import uz.example.foodstore.dto.response.Response;
import uz.example.foodstore.repository.UserRepository;
import uz.example.foodstore.service.UserService;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final UserService userService;

    @PostMapping("/login")
    public ResponseEntity<Response<String>>login(@RequestBody UserLonginDto userLonginDto){
       return userService.login(userLonginDto);
    }
    @PostMapping("/verify")
    public ResponseEntity<String>verify(String token){
        return ResponseEntity.ok(userService.verify(token));
    }
}
