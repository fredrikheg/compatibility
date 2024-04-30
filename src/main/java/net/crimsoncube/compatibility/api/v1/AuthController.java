package net.crimsoncube.compatibility.api.v1;

import jakarta.validation.Valid;
import net.crimsoncube.compatibility.api.v1.request.LoginRequest;
import net.crimsoncube.compatibility.api.v1.request.SignupRequest;
import net.crimsoncube.compatibility.api.v1.response.JwtResponse;
import net.crimsoncube.compatibility.api.v1.response.MessageResponse;
import net.crimsoncube.compatibility.entity.ERole;
import net.crimsoncube.compatibility.entity.Role;
import net.crimsoncube.compatibility.entity.User;
import net.crimsoncube.compatibility.repository.RoleRepository;
import net.crimsoncube.compatibility.repository.UserRepository;
import net.crimsoncube.compatibility.security.jwt.JwtUtils;
import net.crimsoncube.compatibility.security.service.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository rolesRepository;

    @Autowired
    PasswordEncoder passwordEncoder;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest request) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getUsername(), request.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest request) {

        if(userRepository.existsByUsername(request.getUsername())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Username taken"));
        }

        if(userRepository.existsByEmail(request.getEmail())) {
            return ResponseEntity.badRequest().body(new MessageResponse("Email taken"));
        }

        User user = new User(request.getUsername(),request.getEmail(),passwordEncoder.encode(request.getPassword()));
        Set<Role> roles = new HashSet<>();

        Role userRole = rolesRepository.findByName(ERole.ROLE_USER).orElseThrow(() -> new RuntimeException("Role not found"));

        user.getRoles().add(userRole);
        userRepository.save(user);

        // After authentication use the following to access user:
        // UserDetails userDetails = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // userDetails.getUsername()
        // userDetails.getPassword()
        // userDetails.getAuthorities()

        return ResponseEntity.ok(new MessageResponse("User registration successful"));
    }
}
