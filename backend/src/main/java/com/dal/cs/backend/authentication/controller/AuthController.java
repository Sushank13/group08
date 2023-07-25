package com.dal.cs.backend.authentication.controller;

import com.dal.cs.backend.authentication.dataTransferObject.AuthResponseDTO;
import com.dal.cs.backend.authentication.dataTransferObject.LoginDto;
import com.dal.cs.backend.member.DataLayer.IMemberDataLayer;
import com.dal.cs.backend.security.jwt.JWTGenerator;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Duration;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@RestController
@RequestMapping("/unauthenticated")
public class AuthController {

    private AuthenticationManager authenticationManager;
    private IMemberDataLayer iMemberDataLayer;
    private PasswordEncoder passwordEncoder;
    private JWTGenerator jwtGenerator;

    @Autowired
    public AuthController(AuthenticationManager authenticationManager, IMemberDataLayer iMemberDataLayer, PasswordEncoder passwordEncoder, JWTGenerator jwtGenerator) {
        this.authenticationManager = authenticationManager;
        this.iMemberDataLayer = iMemberDataLayer;
        this.passwordEncoder = passwordEncoder;
        this.jwtGenerator = jwtGenerator;
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponseDTO> login(@RequestBody LoginDto loginDto, HttpServletResponse response){
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                loginDto.getEmailID(),
                loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String token = jwtGenerator.generateToken(authentication.getName());

        Collection<? extends GrantedAuthority> roleAut = authentication.getAuthorities();
        List<String> roles = new ArrayList<>();
        for (GrantedAuthority authority: roleAut
        ) {
            roles.add(authority.getAuthority());
        }

        long refreshTokenDuration = 24*60*60;

        Cookie cookie = new Cookie("jwt", jwtGenerator.generateToken(authentication.getName(), refreshTokenDuration));
        cookie.setHttpOnly(true);
        cookie.setMaxAge(24*60*60);
        cookie.setSecure(true);
        cookie.setPath("/");
        response.addCookie(cookie);
        return new ResponseEntity<>(new AuthResponseDTO(token, roles), HttpStatus.OK);
    }
}
