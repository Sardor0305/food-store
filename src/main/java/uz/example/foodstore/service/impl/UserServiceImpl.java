package uz.example.foodstore.service.impl;

import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.UserLonginDto;
import uz.example.foodstore.dto.request.UserRegisterDto;
import uz.example.foodstore.dto.request.UserUpdateDto;
import uz.example.foodstore.dto.response.Response;
import uz.example.foodstore.dto.response.UserDto;
import uz.example.foodstore.entity.User;
import uz.example.foodstore.exeption.AlreadyExistsException;
import uz.example.foodstore.exeption.NotFoundException;
import uz.example.foodstore.jwt.JwtTokenProvider;
import uz.example.foodstore.repository.RoleRepository;
import uz.example.foodstore.repository.UserRepository;
import uz.example.foodstore.service.UserService;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto save(final UserRegisterDto userRegisterDto) {
        if (findByUsername(userRegisterDto.getUsername()) != null) {
            throw new AlreadyExistsException("username");
        }
        return new UserDto(userRepository.save(User.builder().username(userRegisterDto.getUsername()).password(passwordEncoder.encode(userRegisterDto.getPassword())).name(userRegisterDto.getName()).condition(userRegisterDto.getCondition()).roles(userRegisterDto.getRolesId().stream().map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("role"))).toList())
                .build()));
    }


    @Override
    public UserDto findUserDtoByUsername(final String username) {
        return userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException("user"));
    }

    @Override
    public Boolean deleteById(final Long id) {
        userRepository.findById(id).orElseThrow(() -> new NotFoundException("user"));
        userRepository.deleteById(id);
        return true;
    }

    @Override
    public UserDto updateById(final UserUpdateDto userUpdateDto) {
        final User user = userRepository.findById(userUpdateDto.getId()).orElseThrow(() -> new NotFoundException("user"));
        user.setUsername(userUpdateDto.getUsername());
        user.setPassword(userUpdateDto.getPassword());
        user.setName(userUpdateDto.getName());
        user.setCondition(userUpdateDto.getCondition());
        user.setRoles(userUpdateDto.getRolesId().stream().map(roleId -> roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("role"))).toList());
        return new UserDto(userRepository.save(user));

    }

    @Override
    public ResponseEntity<Response<String>> login(final UserLonginDto userLonginDto) {
        final User user = userRepository.findByUsername(userLonginDto.getUsername()).orElseThrow(() -> new NotFoundException("user"));
        if (!passwordEncoder.matches(userLonginDto.getPassword(), user.getPassword())) {
            throw new NotFoundException("password");
        }
        return ResponseEntity.ok(new Response<>(jwtTokenProvider.generateTokenForAuth(user)));

    }

    @Override
    public User findByUsername(final String username) {
        final User user = userRepository.findByUsername(username).orElseThrow(() -> new NotFoundException("user"));
        return user;
    }

    @Override
    public String verify(String token) {
        final Claims claims = jwtTokenProvider.parseAllClaims(token);
        return jwtTokenProvider.generateTokenForAuth(findByUsername(claims.getSubject()));
    }
}
