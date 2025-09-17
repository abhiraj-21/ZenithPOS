package io.github.abhiraj.service.impl;

import io.github.abhiraj.configuration.JwtProvider;
import io.github.abhiraj.entities.User;
import io.github.abhiraj.entities.UserRole;
import io.github.abhiraj.exceptions.UserException;
import io.github.abhiraj.mapper.UserMapper;
import io.github.abhiraj.payload.dto.UserDto;
import io.github.abhiraj.payload.response.AuthResponse;
import io.github.abhiraj.repositories.UserRepository;
import io.github.abhiraj.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtProvider jwtProvider;
    private final CustomUserImplementation customUserImplementation;


    @Override
    public AuthResponse signup(UserDto userDto) throws UserException {
        User user = userRepository.findByEmail(userDto.getEmail());

        if(user != null){
            throw new UserException("Email is already registered!!");
        }
        if(userDto.getRole().equals(UserRole.ROLE_ADMIN)){
            throw new UserException("Role admin is not allowed");
        }

        User newUser = new User();
        newUser.setEmail(userDto.getEmail());
        newUser.setPassword(passwordEncoder.encode(userDto.getPassword()));
        newUser.setRole(userDto.getRole());
        newUser.setFullName(userDto.getFullName());
        newUser.setPhone(userDto.getPhone());
        newUser.setLastLoginAt(LocalDateTime.now());
        newUser.setCreatedAt(LocalDateTime.now());
        newUser.setUpdatedAt(LocalDateTime.now());

        User savedUser = userRepository.save(newUser);

        Authentication authentication = new UsernamePasswordAuthenticationToken(userDto.getEmail(), userDto.getPassword());
        SecurityContextHolder.getContext().setAuthentication(authentication);

        String jwt = jwtProvider.generateJwtToken(authentication);

        AuthResponse authResponse = new AuthResponse();
        authResponse.setJwt(jwt);
        authResponse.setMessage("Registered Successfully");
        authResponse.setUser(UserMapper.toDto(savedUser));

        return authResponse;
    }

    @Override
    public AuthResponse login(UserDto userDto) {
        return null;
    }
}
