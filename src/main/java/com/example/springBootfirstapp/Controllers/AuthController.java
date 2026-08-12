package com.example.springBootfirstapp.Controllers;

import com.example.springBootfirstapp.*;
import com.example.springBootfirstapp.Authentication.JwtRequest;
import com.example.springBootfirstapp.Authentication.JwtResponse;
import com.example.springBootfirstapp.Authentication.RefreshJwtRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/auth")
@CrossOrigin(origins = "*")
public class AuthController {
    private final AuthService authService;

    public AuthController(AuthService authservice) {
        this.authService = authservice;
    }
    @PostMapping("login")
    public ResponseEntity<JwtResponse> login(@RequestBody JwtRequest authrequest ){
        final JwtResponse token = authService.login(authrequest);
        return ResponseEntity.ok(token);
    }
    @PostMapping("token")
    public ResponseEntity<JwtResponse> getNewAccessToken(@RequestBody RefreshJwtRequest request){
        final JwtResponse token = authService.getAccessToken(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("refresh")
    public ResponseEntity<JwtResponse> getNewRefreshToken(@RequestBody RefreshJwtRequest request) {
        final JwtResponse token = authService.refresh(request.getRefreshToken());
        return ResponseEntity.ok(token);
    }
    @PostMapping("register")
    public ResponseEntity<String> register(@RequestBody RegisterRequest request){
        final String register= authService.register(request);
        return ResponseEntity.ok(register);
    }
}
