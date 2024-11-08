package com.java.TodoManagement.Service;

import com.java.TodoManagement.Dto.LoginDto;
import com.java.TodoManagement.Dto.RegisterDto;

public interface AuthService {
    public String register(RegisterDto registerDto);
    public String login(LoginDto loginDto);
}
