package com.project.notesv2.login;

import com.project.notesv2.user.UserService;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;

@RestController
@RequestMapping("/api")
public class LoginRestController {
    @Autowired
    private UserService userService;
    @GetMapping("/login")
    public ResponseEntity<?> getLogin() {
        return ResponseEntity.ok("Вы на странице логина");
    }
    @PostMapping("/login")
    public String postLogin(@RequestBody LoginDTO loginRequest, HttpServletRequest request) {
        try {
            request.login(loginRequest.getUsername(), loginRequest.getPassword());
            return "Вы успешно вошли в аккаунт";

        } catch (ServletException e) {
            e.getMessage();
            return "Ошибка входа на аккаунт";
        }
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request) throws  ServletException {
        if (request.getUserPrincipal() != null) {
            request.logout();
            return "Вы удачно вышли с аккаунта";
        }
        return "Вы не выполняли вход";

    }

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody RegisterRequest registerRequest) {
        try {
            return ResponseEntity
                    .ok(userService.createUser(userService.convertRegisterRequestToUser(registerRequest)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
