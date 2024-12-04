package com.project.kursachv2.Role;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {
    @Autowired
    private RoleRepository roleRepository;

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Role getRoleById(long id) {
        return roleRepository.getReferenceById(id);
    }

    public Role createRole(Role role) {
        return roleRepository.save(role);
    }

    public Role deleteRole(long id) {
        Role role = getRoleById(id);
        roleRepository.deleteById(id);
        return role;
    }

    public Role updateRole(Role role, long id) {
        role.setId(id);
        return roleRepository.save(role);
    }

    public Role convertDTOToRole(RoleDTO roleDTO) {
        Role role = new Role();
        role.setRole(roleDTO.getRole());
        return role;
    }
}
