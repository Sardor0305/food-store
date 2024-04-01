package uz.example.foodstore.component;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import uz.example.foodstore.entity.Role;
import uz.example.foodstore.entity.User;
import uz.example.foodstore.repository.RoleRepository;
import uz.example.foodstore.repository.UserRepository;

import java.util.List;

@Component
@RequiredArgsConstructor
public class DataLoader implements CommandLineRunner {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        final String username = "admin";
        if (!userRepository.existsByUsername(username)) {
            Role roleAdmin = new Role(1L, "ROLE_ADMIN");
            Role roleWorker = new Role(1L, "ROLE_WORKER");
            List<Role> roles = roleRepository.saveAll(List.of(roleAdmin, roleWorker));
            User user = new User(1L, "Admin", username, passwordEncoder.encode("admin_password"), null, roles);
            userRepository.save(user);
        }
    }
}
