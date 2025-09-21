package io.github.abhiraj.service;

import io.github.abhiraj.exceptions.UserException;
import io.github.abhiraj.payload.dto.UserDto;
import io.github.abhiraj.payload.response.AuthResponse;

public interface AuthService {

    AuthResponse signup(UserDto userDto) throws UserException;
    AuthResponse login(UserDto userDto) throws UserException;

}
