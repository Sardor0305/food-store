package uz.example.foodstore.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.RoleCreateDto;
import uz.example.foodstore.dto.response.RoleDto;

import java.util.List;

@Service
public interface RoleService {
    RoleDto save(final RoleCreateDto roleCreateDto);
    Boolean deleteById(final Long id);
    RoleDto updateById(final RoleDto roleDto);
    List<RoleDto> allRole();
    RoleDto getRoleById(final Long id);

}
