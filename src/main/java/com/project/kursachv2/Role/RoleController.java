package com.project.kursachv2.Role;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/role")
public class RoleController {
    @Autowired
    private RoleService roleService;

    @GetMapping
    public List<Role> getAllRoles() {
        return roleService.getAllRoles();
    }

    @GetMapping("/{id}")
    public Role getRoleById(@PathVariable long id) {
        return roleService.getRoleById(id);
    }

    @PostMapping
    public void createRole(@RequestBody RoleDTO roleDTO) {
        roleService.createRole(roleService.convertDTOToRole(roleDTO));
    }

    @DeleteMapping("/{id}")
    public Role deleteRole(@PathVariable long id) {
        return roleService.deleteRole(id);
    }

    @PutMapping("/{id}")
    public Role updateRole(@RequestBody RoleDTO roleDTO, @PathVariable long id) {
        return roleService.updateRole(roleService.convertDTOToRole(roleDTO), id);
    }
}
