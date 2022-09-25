package edu.miu.cs544.cloudgetway.controller;

import edu.miu.cs544.cloudgetway.model.*;
import edu.miu.cs544.cloudgetway.security.JwtTokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static edu.miu.cs544.cloudgetway.Constants.API_GATEWAY_PREDICATE;

@RestController
public class JwtAuthenticationController {
    @Autowired
    private RestTemplate restTemplate;
    @Autowired
    private final JwtTokenUtil jwtTokenUtil;

    public JwtAuthenticationController(JwtTokenUtil jwtTokenUtil) {
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(  "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest) {
        AuthenticationStatus status = authenticate(authenticationRequest.getUsername(), authenticationRequest.getPassword());

        if (!status.getIsAuthenticated()) {
            List<String> details = new ArrayList<>();
            details.add(status.getMessage());
            ErrorResponseDto error = new ErrorResponseDto(new Date(), HttpStatus.UNAUTHORIZED.value(), "UNAUTHORIZED", details, "uri");
            return new ResponseEntity<>(error, HttpStatus.UNAUTHORIZED);
        }

        final String token = jwtTokenUtil.generateToken(authenticationRequest.getUsername());
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private AuthenticationStatus authenticate(String username, String password) {
        AuthenticationStatus status;
        AppUser appUser = restTemplate.getForObject("http://USER-SERVICE/users/login/"+username ,AppUser.class);
        System.out.println(appUser.getUserName());
        if (!username.equals(appUser.getUserName()) && !password.equals(appUser.getPassword())) {
            status = new AuthenticationStatus(false, "Invalid Username/Password");
        }
        else {
            status = new AuthenticationStatus(true, "Authentication Successful");
        }

        return status;
    }

}
