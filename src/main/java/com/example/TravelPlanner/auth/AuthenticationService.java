package com.example.TravelPlanner.auth;

import com.example.TravelPlanner.auth.common.*;
import com.example.TravelPlanner.auth.config.JwtService;
import com.example.TravelPlanner.auth.entities.CustomUserDetails;
import com.example.TravelPlanner.auth.entities.Role;
import com.example.TravelPlanner.auth.entities.User;
import com.example.TravelPlanner.common.exceptions.custom.BadRequest;
import com.example.TravelPlanner.common.exceptions.custom.entitynotfound.UserNotFoundException;
import com.example.TravelPlanner.common.utils.mappers.MapperUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;
    private final MapperUtil mapperUtil;

    public AuthenticationResponse register(RegisterRequest request) {
        boolean usernameExists = userRepository.existsByUsername(request.getUsername());
        if (usernameExists) {
            throw new BadRequest("Username already taken.");
        }

        boolean emailExists = userRepository.existsByEmail(request.getEmail());
        if (emailExists) {
            throw new BadRequest("Email already taken.");
        }

        var user = User.builder()
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .createdAt(LocalDateTime.now())
                .build();
        userRepository.save(user);
        var jwtToken = jwtService.generateToken(mapperUtil.map(user, CustomUserDetails.class));
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        Optional<User> user = userRepository.findByUsername(request.getUsername());
        if(user.isEmpty()){
            throw new UserNotFoundException(request.getUsername());
        }
        var jwtToken = jwtService.generateToken(mapperUtil.map(user, CustomUserDetails.class));
        return AuthenticationResponse.builder().token(jwtToken).build();

    }
    public LogoutResponse logout(String authHeader) {
        String jwt = authHeader.substring(7);
        jwtService.blacklistToken(jwt);
        return LogoutResponse.builder().message("successfully logout").build();
    }

}
