package com.project.kursachv2.Group;

import com.project.kursachv2.Organization.Organization;
import com.project.kursachv2.Organization.OrganizationDTO;
import com.project.kursachv2.Organization.OrganizationService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Validated
@RequestMapping("api/group")
public class GroupController {
    @Autowired
    private GroupService groupService;
    // Получение студента по индетификатору
    @GetMapping("/{id}")
    public Group getGroupById(@PathVariable long id) {
        return groupService.getGroupById(id);
    }
    // Получение всех студентов
    @GetMapping
    public List<Group> getAllGroups() {
        return groupService.getAllGroups();
    }
    // Запись нового студента
    @PostMapping
    public Group addGroup(@Valid @RequestBody GroupDTO groupDTO) {
        return groupService.addGroup(groupService.convertDTOtoGroup(groupDTO));
    }
    // Обновление студента по индетификатору
    @PutMapping("/{id}")
    public Group updateGroupById(@PathVariable long id, @Valid @RequestBody GroupDTO groupDTO) {
        return groupService.updateGroupById(groupService.convertDTOtoGroup(groupDTO), id);
    }
    // Удаление студента по индетификатору
    @DeleteMapping("/{id}")
    public void deleteGroupById(@PathVariable long id) {
        groupService.deleteGroupById(id);
    }
}
