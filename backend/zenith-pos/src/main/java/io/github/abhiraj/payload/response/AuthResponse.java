package io.github.abhiraj.payload.response;

import io.github.abhiraj.payload.dto.UserDto;
import lombok.Data;

@Data
public class AuthResponse {
    private String jwt;
    private String message;
    private UserDto user;
}
