package com.project.notesv2.role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRole(String roleName) {
        if (roleRepository.existsByName(roleName)) {
            return roleRepository.getRoleByName(roleName);
        }
        throw  new DataIntegrityViolationException("Роль с именем " + roleName + " уже существует");
    }

    public void addRole(Role role) {
        if (roleRepository.existsByName(role.getName())) {
            throw new DataIntegrityViolationException("Роль с именем " + role.getName() + " уже существует");
        }
        roleRepository.save(role);
    }

    public Role convetRequestToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setName(roleDTO.getName());
        return role;
    }

}
