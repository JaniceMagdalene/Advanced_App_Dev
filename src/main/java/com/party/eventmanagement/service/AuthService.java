package com.party.eventmanagement.service;

import com.party.eventmanagement.dto.request.LoginRequest;
import com.party.eventmanagement.dto.request.RegisterRequest;
import com.party.eventmanagement.dto.response.LoginResponse;

public interface AuthService {
    String register(RegisterRequest registerRequest);

    LoginResponse login(LoginRequest loginRequest);
   
}
