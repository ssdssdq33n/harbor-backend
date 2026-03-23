package com.hungnv.backend.modules.yard.controller;

import com.hungnv.backend.modules.yard.entity.Yard;
import com.hungnv.backend.modules.yard.repository.YardRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/yards")
public class YardController {
    private final YardRepository yardRepository;

    public YardController(YardRepository yardRepository) {
        this.yardRepository = yardRepository;
    }

    @GetMapping
    public ResponseEntity<List<Yard>> getAll() {
        return ResponseEntity.ok(yardRepository.findAll());
    }
}

