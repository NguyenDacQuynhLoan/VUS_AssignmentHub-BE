package com.edusystem.Controllers;

//import com.edusystem.Configuration.JWTUtil;
//import com.edusystem.Configuration.SecurityConfig;
import com.edusystem.Configuration.JWTUtil;
import com.edusystem.Configuration.SecurityConfig;
import com.edusystem.Assets.Models.Login;
import com.edusystem.Repositories.Authen.AuthenticateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
public class LoginController {
    private  final SecurityConfig _securityConfig;
    private final AuthenticationManager _authenticationManager;
    private final AuthenticateRepository _authenticateRepository;
    private final JWTUtil _jwtUtil;

    @PostMapping("/login")
    public ResponseEntity<String> isLogged(@RequestBody Login model)
    {
        var temp = model.getPassword();
        _authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(model.getEmail(),model.getPassword())
        );

        final UserDetails userService = _authenticateRepository.findEmail(model.getEmail());
        if(userService != null){
            String token = _jwtUtil.generateToken(userService);
          return ResponseEntity.ok(token);
        }
        return ResponseEntity.status(404).body("Can not found");
    }
}
