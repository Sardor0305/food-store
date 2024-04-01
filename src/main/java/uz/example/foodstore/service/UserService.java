package uz.example.foodstore.service;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.UserLonginDto;
import uz.example.foodstore.dto.request.UserRegisterDto;
import uz.example.foodstore.dto.request.UserUpdateDto;
import uz.example.foodstore.dto.response.Response;
import uz.example.foodstore.dto.response.UserDto;
import uz.example.foodstore.entity.User;

@Service
public interface UserService {
    UserDto save(final UserRegisterDto userRegisterDto);
    UserDto findUserDtoByUsername(final String username);
    Boolean deleteById(final Long id);
    UserDto updateById(final UserUpdateDto userUpdateDto);
    ResponseEntity<Response<String>> login(final UserLonginDto userLonginDto);
    User findByUsername(final String username);

    String verify(final String token);
}
