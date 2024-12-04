package com.project.kursachv2.Organization;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("/api/organization")
public class OrganizationController {
    @Autowired
    private OrganizationService organizationService;
    // Получение студента по индетификатору
    @GetMapping("/{id}")
    public Organization getOrganizationById(@PathVariable long id) {
        return organizationService.getOrganizationById(id);
    }
    // Получение всех студентов
    @GetMapping
    public List<Organization> getAllOrganizations() {
        return organizationService.getAllOrganizations();
    }
    // Запись нового студента
    @PostMapping
    public Organization addOrganization(@Valid @RequestBody OrganizationDTO organizationDTO) {
        return organizationService.addOrganization(organizationService.convertDTOtoOrganization(organizationDTO));
    }
    // Обновление студента по индетификатору
    @PutMapping("/{id}")
    public Organization updateOrganizationById(@PathVariable long id, @Valid @RequestBody OrganizationDTO organizationDTO) {
        return organizationService.updateOrganizationById(organizationService.convertDTOtoOrganization(organizationDTO), id);
    }
    // Удаление студента по индетификатору
    @DeleteMapping("/{id}")
    public void deleteOrganizationById(@PathVariable long id) {
        organizationService.deleteOrganizationById(id);
    }
}
