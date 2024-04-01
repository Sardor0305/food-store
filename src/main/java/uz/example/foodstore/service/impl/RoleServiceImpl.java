package uz.example.foodstore.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import uz.example.foodstore.dto.request.RoleCreateDto;
import uz.example.foodstore.dto.response.RoleDto;
import uz.example.foodstore.entity.Role;
import uz.example.foodstore.entity.User;
import uz.example.foodstore.exeption.NotFoundException;
import uz.example.foodstore.repository.RoleRepository;
import uz.example.foodstore.service.RoleService;

import java.util.List;
@Service
@AllArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final RoleRepository roleRepository;
    @Override
    public RoleDto save(final RoleCreateDto roleCreateDto) {
        return new RoleDto(roleRepository.save(Role.builder()
                        .value(roleCreateDto.getValue())
                .build()));
    }

    @Override
    public Boolean deleteById(final Long id) {
        roleRepository.findById(id).orElseThrow(() -> new NotFoundException("Role"));
        roleRepository.deleteById(id);
        return true;
    }

    @Override
    public RoleDto updateById(final RoleDto roleDto) {
      final Role role = roleRepository.findById(roleDto.getId()).orElseThrow(() ->
              new NotFoundException("role"));
      role.setValue(roleDto.getValue());
      return new RoleDto(roleRepository.save(role));
    }

    @Override
    public List<RoleDto> allRole() {
        return roleRepository.getAllRole().orElse(null);
    }

    @Override
    public RoleDto getRoleById(final Long id) {
        return roleRepository.getByRoleId(id).orElseThrow(() -> new NotFoundException("role"));
    }
}
