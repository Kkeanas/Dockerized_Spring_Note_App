package com.project.notesv2.role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/role")
public class RoleRestController {
    @Autowired
    private RoleService roleService;
    @GetMapping
    public ResponseEntity<?> getRoles() {
        return ResponseEntity.ok(roleService.getAllRoles());
    }
    @PostMapping
    public ResponseEntity<?> addRole(@RequestBody RoleDTO roleDTO) {
        try {
            roleService.addRole(roleService.convetRequestToRole(roleDTO));
            return ResponseEntity.ok(roleService.getAllRoles());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
