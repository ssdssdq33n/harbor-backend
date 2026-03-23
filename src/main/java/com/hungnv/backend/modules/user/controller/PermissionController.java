package com.hungnv.backend.modules.user.controller;

import com.hungnv.backend.modules.user.entity.Permission;
import com.hungnv.backend.modules.user.repository.PermissionRepository;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/permissions")
public class PermissionController {
    private final PermissionRepository permissionRepository;

    public PermissionController(PermissionRepository permissionRepository) {
        this.permissionRepository = permissionRepository;
    }

    @GetMapping
    public ResponseEntity<List<Permission>> list() {
        return ResponseEntity.ok(permissionRepository.findAll());
    }

    @PostMapping
    public ResponseEntity<Permission> create(@Valid @RequestBody Permission permission) {
        permission.setPermissionId(null);
        return ResponseEntity.ok(permissionRepository.save(permission));
    }
}

