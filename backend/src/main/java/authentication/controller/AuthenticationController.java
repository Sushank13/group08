package authentication.controller;

import authentication.service.AuthenticationService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/authentication")

public class AuthenticationController {
    private final AuthenticationService authenticationservice;

    public AuthenticationController(AuthenticationService authenticationservice) {
        this.authenticationservice = authenticationservice;
    }

    @PostMapping("/signup")
    public ResponseEntity signup(){
        return ResponseEntity.ok(authenticationservice);
    }

}
