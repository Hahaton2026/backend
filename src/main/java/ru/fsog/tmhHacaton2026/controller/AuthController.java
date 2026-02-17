package ru.fsog.tmhHacaton2026.controller;

import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.web.bind.annotation.*;
import ru.fsog.tmhHacaton2026.dto.LoginRequestDTO;
import ru.fsog.tmhHacaton2026.entity.User;
import ru.fsog.tmhHacaton2026.repository.UserRepository;
import ru.fsog.tmhHacaton2026.security.JwtUtils;

import java.util.Map;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationManager authenticationManager;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;


//    @PostMapping("/login")
//    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
//        try {
//            authenticationManager.authenticate(
//                    new UsernamePasswordAuthenticationToken(
//                            loginRequestDTO.getUsername(),
//                            loginRequestDTO.getPassword()
//                    )
//            );
//
//            String token = jwtUtils.generateToken(loginRequestDTO.getUsername());
//            return ResponseEntity.ok(Map.of("token", token));
//
//        } catch (org.springframework.security.authentication.BadCredentialsException e) {
//            return ResponseEntity.status(401).body(Map.of("error", "Неверный логин или пароль"));
//        }
//    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequestDTO loginRequestDTO) {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            loginRequestDTO.getUsername(),
                            loginRequestDTO.getPassword()
                    )
            );
            String token = jwtUtils.generateToken(loginRequestDTO.getUsername());

            String role = userRepository.findByUsername(loginRequestDTO.getUsername())
                    .map(User::getRole)
                    .orElse("USER");

            return ResponseEntity.ok(Map.of(
                    "token", token,
                    "role", role
            ));

        } catch (org.springframework.security.authentication.BadCredentialsException e) {
            return ResponseEntity.status(401).body(Map.of("error", "Неверный логин или пароль"));
        } catch (Exception e) {
            return ResponseEntity.status(403).body(Map.of("error", "Доступ запрещен: " + e.getMessage()));
        }
    }

}
