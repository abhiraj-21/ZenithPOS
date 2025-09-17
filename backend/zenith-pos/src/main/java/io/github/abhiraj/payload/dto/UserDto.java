package io.github.abhiraj.payload.dto;

import io.github.abhiraj.entities.UserRole;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Email;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class UserDto {

    private Long id;

    private String fullName;

    private String email;
    private String phone;

    private UserRole role;

    private String password;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private LocalDateTime lastLoginAt;
}
