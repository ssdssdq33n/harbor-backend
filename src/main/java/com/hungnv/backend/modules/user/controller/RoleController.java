package com.hungnv.backend.modules.user.controller;

import com.hungnv.backend.modules.user.entity.Role;
import com.hungnv.backend.modules.user.repository.RoleRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/roles")
public class RoleController {
    private final RoleRepository roleRepository;

    public RoleController(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @GetMapping
    public ResponseEntity<List<Role>> list() {
        return ResponseEntity.ok(roleRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Role> create(@Valid @RequestBody Role role) {
        role.setRoleId(null);
        return ResponseEntity.ok(roleRepository.save(role));
    }
}

