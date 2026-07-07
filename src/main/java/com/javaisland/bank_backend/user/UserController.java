package com.javaisland.bank_backend.user;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🧠 1. ENDPOINT PER LA REGISTRAZIONE (Accessibile da tutti)
    @PostMapping("/register")
    // 📌 Cambiato da @RequestBody User user a @RequestBody UserRegisterDTO registerDTO
    public ResponseEntity<User> register(@Valid @RequestBody UserRegisterDTO registerDTO) {
        User registeredUser = userService.registerUser(registerDTO);
        return new ResponseEntity<>(registeredUser, HttpStatus.CREATED);
    }

    // 🧠 2. ENDPOINT PER ESPORTARE I CORRENTISTI (Funzione per Dipendente)
    @GetMapping("/export")
    public ResponseEntity<String> exportCustomers(@RequestParam String filePath) {
        userService.exportCustomersToFile(filePath);
        return ResponseEntity.ok("File esportato con successo in: " + filePath);
    }
}