package com.hungnv.backend.modules.auth.controller;

import com.hungnv.backend.modules.auth.dto.request.ForgotPasswordRequest;
import com.hungnv.backend.modules.auth.dto.request.LoginRequest;
import com.hungnv.backend.modules.auth.dto.request.LogoutRequest;
import com.hungnv.backend.modules.auth.dto.request.RegisterRequest;
import com.hungnv.backend.modules.auth.dto.request.ResetPasswordRequest;
import com.hungnv.backend.modules.auth.dto.response.TokenResponse;
import com.hungnv.backend.modules.auth.service.AuthService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private final AuthService authService;
    public AuthController(AuthService authService) {
        this.authService = authService;
    }

    @PostMapping("/login")
    public ResponseEntity<TokenResponse> login(@Valid @RequestBody LoginRequest request) {
        String token = authService.login(request);
        return ResponseEntity.ok(new TokenResponse(token));
    }

    @PostMapping("/register")
    public ResponseEntity<Void> register(@Valid @RequestBody RegisterRequest request) {
        authService.register(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/forgot-password")
    public ResponseEntity<Void> forgotPassword(@Valid @RequestBody ForgotPasswordRequest request) {
        authService.forgotPassword(request.getEmail());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/reset-password")
    public ResponseEntity<Void> resetPassword(@Valid @RequestBody ResetPasswordRequest request) {
        authService.resetPassword(request.getToken(), request.getNewPassword());
        return ResponseEntity.ok().build();
    }

    @PostMapping("/logout")
    public ResponseEntity<Void> logout(@Valid @RequestBody LogoutRequest request) {
        authService.logout(request.getToken());
        return ResponseEntity.ok().build();
    }
}

