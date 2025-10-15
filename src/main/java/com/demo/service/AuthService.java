package com.demo.service;


import com.demo.auth.JwtUtil;
import com.demo.dto.*;
import com.demo.model.Employee;
import com.demo.model.Role;
import com.demo.repositories.EmpRepositories;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.security.authentication.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthService {

    private final AuthenticationManager authenticationManager;
    private final UserDetailsService userDetailsService;
    private final EmpRepositories employeeRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtUtil jwtUtil;

    @Autowired
    public AuthService(AuthenticationManager authenticationManager,
                       UserDetailsService userDetailsService,
                       EmpRepositories employeeRepository,
                       PasswordEncoder passwordEncoder,
                       JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.userDetailsService = userDetailsService;
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtUtil = jwtUtil;
    }

    // ================================
    // LOGIN
    // ================================
    public AuthResponse authenticate(AuthRequest request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );

        final UserDetails userDetails = userDetailsService.loadUserByUsername(request.getUsername());
        final String token = jwtUtil.generateToken(userDetails);
        final String username = userDetails.getUsername();
        final Role role = jwtUtil.extractRole(token);

        return new AuthResponse(true, "Login successful", username, role, token);
    }

    // ================================
    // REGISTER
    // ================================
    public ResponseEntity<ApiResponse> register(RegisterRequest request) {
        if (employeeRepository.findByUsername(request.getUsername()).isPresent()) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body(new ApiResponse(false, "Username already exists"));
        }

        Employee employee = new Employee();
        employee.setUsername(request.getUsername());
        employee.setPassword(passwordEncoder.encode(request.getPassword()));
        employee.setRole(Role.USER);
        employee.setFirstName(request.getFirstName());
        employee.setLastName(request.getLastName());
        employee.setAge(request.getAge());


        employeeRepository.save(employee);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(new ApiResponse(true, "User registered successfully"));
    }
}
