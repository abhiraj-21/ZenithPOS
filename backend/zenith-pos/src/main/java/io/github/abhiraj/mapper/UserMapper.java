package io.github.abhiraj.mapper;

import io.github.abhiraj.entities.User;
import io.github.abhiraj.payload.dto.UserDto;

public class UserMapper {

    public static UserDto toDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setCreatedAt(user.getCreatedAt());
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setRole(user.getRole());
        userDto.setPhone(user.getPhone());
        userDto.setFullName(user.getFullName());
        userDto.setUpdatedAt(user.getUpdatedAt());
        userDto.setLastLoginAt(user.getLastLoginAt());
        return userDto;
    }
}
