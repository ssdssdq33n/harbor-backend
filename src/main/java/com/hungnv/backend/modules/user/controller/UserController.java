package com.hungnv.backend.modules.user.controller;

import com.hungnv.backend.modules.user.entity.Role;
import com.hungnv.backend.modules.user.entity.SystemLog;
import com.hungnv.backend.modules.user.entity.UserAddress;
import com.hungnv.backend.modules.user.entity.UserProfile;
import com.hungnv.backend.modules.user.entity.User;
import com.hungnv.backend.modules.user.repository.SystemLogRepository;
import com.hungnv.backend.modules.user.repository.UserAddressRepository;
import com.hungnv.backend.modules.user.repository.UserProfileRepository;
import com.hungnv.backend.modules.user.repository.RoleRepository;
import com.hungnv.backend.modules.user.repository.UserRepository;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {
    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final UserProfileRepository userProfileRepository;
    private final UserAddressRepository userAddressRepository;
    private final SystemLogRepository systemLogRepository;

    public UserController(
            UserRepository userRepository,
            RoleRepository roleRepository,
            UserProfileRepository userProfileRepository,
            UserAddressRepository userAddressRepository,
            SystemLogRepository systemLogRepository
    ) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.userProfileRepository = userProfileRepository;
        this.userAddressRepository = userAddressRepository;
        this.systemLogRepository = systemLogRepository;
    }

    @GetMapping
    public ResponseEntity<List<User>> list() {
        return ResponseEntity.ok(userRepository.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> get(@PathVariable Integer id) {
        return ResponseEntity.of(userRepository.findById(id));
    }

    @PostMapping("/{id}/roles")
    @Transactional
    public ResponseEntity<User> assignRole(@PathVariable Integer id, @Valid @RequestBody AssignRoleRequest request) {
        User user = userRepository.findById(id).orElseThrow();
        Role role = roleRepository.findById(request.getRoleId()).orElseThrow();
        user.getRoles().add(role);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/{id}/profile")
    public ResponseEntity<UserProfile> profile(@PathVariable Integer id) {
        return ResponseEntity.of(userProfileRepository.findByUserId(id));
    }

    @PutMapping("/{id}/profile")
    public ResponseEntity<UserProfile> upsertProfile(@PathVariable Integer id, @Valid @RequestBody UserProfile profile) {
        UserProfile p = userProfileRepository.findByUserId(id).orElseGet(() -> UserProfile.builder().userId(id).build());
        p.setGender(profile.getGender());
        p.setDateOfBirth(profile.getDateOfBirth());
        p.setNationalId(profile.getNationalId());
        return ResponseEntity.ok(userProfileRepository.save(p));
    }

    @GetMapping("/{id}/addresses")
    public ResponseEntity<List<UserAddress>> addresses(@PathVariable Integer id) {
        return ResponseEntity.ok(userAddressRepository.findByUserId(id));
    }

    @PostMapping("/{id}/addresses")
    public ResponseEntity<UserAddress> addAddress(@PathVariable Integer id, @Valid @RequestBody UserAddress address) {
        address.setAddressId(null);
        address.setUserId(id);
        return ResponseEntity.ok(userAddressRepository.save(address));
    }

    @GetMapping("/{id}/logs")
    public ResponseEntity<List<SystemLog>> logs(@PathVariable Integer id) {
        return ResponseEntity.ok(systemLogRepository.findByUserIdOrderByCreatedAtDesc(id));
    }

    public static class AssignRoleRequest {
        @NotNull
        private Integer roleId;

        public Integer getRoleId() {
            return roleId;
        }

        public void setRoleId(Integer roleId) {
            this.roleId = roleId;
        }
    }
}
