package com.hungnv.backend.modules.auth.service;

import com.hungnv.backend.modules.auth.dto.request.LoginRequest;
import com.hungnv.backend.modules.auth.dto.request.RegisterRequest;

public interface AuthService {
    String login(LoginRequest request);
    void register(RegisterRequest request);
    void forgotPassword(String email);
    void resetPassword(String token, String newPassword);
    void logout(String token);
}
