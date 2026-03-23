package com.hungnv.backend.common.config;

import com.hungnv.backend.modules.user.entity.Permission;
import com.hungnv.backend.modules.user.entity.Role;
import com.hungnv.backend.modules.user.entity.User;
import com.hungnv.backend.modules.user.repository.PermissionRepository;
import com.hungnv.backend.modules.user.repository.RoleRepository;
import com.hungnv.backend.modules.user.repository.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
public class DataSeeder implements CommandLineRunner {
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataSeeder(RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) {
        Permission pAdmin = permissionRepository.findByPermissionName("ADMIN").orElseGet(() ->
                permissionRepository.save(Permission.builder().permissionName("ADMIN").description("Admin access").build()));

        Role adminRole = roleRepository.findByRoleName("ADMIN").orElseGet(() ->
                roleRepository.save(Role.builder().roleName("ADMIN").permissions(Set.of(pAdmin)).build()));

        userRepository.findByUsername("admin").orElseGet(() -> {
            User admin = User.builder()
                    .username("admin")
                    .password(passwordEncoder.encode("admin123"))
                    .fullName("Administrator")
                    .email("admin@local")
                    .status((short) 1)
                    .roles(new HashSet<>(Set.of(adminRole)))
                    .build();
            return userRepository.save(admin);
        });
    }
}

