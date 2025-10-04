package vn.iotstar.jwt_spring.controller;


import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import vn.iotstar.jwt_spring.entity.User;
import vn.iotstar.jwt_spring.model.LoginResponse;
import vn.iotstar.jwt_spring.model.LoginUserModel;
import vn.iotstar.jwt_spring.model.RegisterUserModel;
import vn.iotstar.jwt_spring.service.AuthenticationService;
import vn.iotstar.jwt_spring.service.JwtService;


@Controller
@RequestMapping("/auth")
public class AuthenticationController {
    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    // View endpoints for serving HTML pages
    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    @GetMapping("/profile")
    public String profilePage() {
        return "profile";
    }

    // REST API endpoints for Ajax calls
    @PostMapping("/signup")
    @ResponseBody
    @Transactional
    public ResponseEntity<User> register(@RequestBody RegisterUserModel registerUser) {
        User registeredUser = authenticationService.signup(registerUser);
        return ResponseEntity.ok(registeredUser);
    }

    @PostMapping(path = "/login")
    @ResponseBody
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) {
        User authenticatedUser = authenticationService.authenticate(loginUser);
        String jwtToken = jwtService.generateToken(authenticatedUser);
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setToken(jwtToken);
        loginResponse.setExpiresIn(jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}