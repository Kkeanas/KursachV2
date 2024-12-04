package com.project.kursachv2.Group;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class GroupService {
    @Autowired
    private GroupRepository groupRepository;

    public boolean groupExitsById(long id) {
        return groupRepository.existsById(id);
    }

    public Group getGroupById(long id) {
        return groupRepository.getReferenceById(id);
    }

    public List<Group> getAllGroups() {
        return groupRepository.findAll();
    }

    public Group addGroup(Group group) {
        return groupRepository.save(group);
    }

    public Group updateGroupById(Group group, long id) {
        if (groupExitsById(id)) {
            group.setId(id);
            return groupRepository.save(group);
        } else {
            throw new DataIntegrityViolationException("Группа с id="+id+" не существует");
        }
    }

    public void deleteGroupById(long id) {
        if (groupExitsById(id)) {
            groupRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Группа с id="+id+" не существует");
        }
    }

    public Group convertDTOtoGroup(GroupDTO groupDTO) {
        Group group = new Group();
        group.setGrpName(groupDTO.getGrpName());
        return group;
    }
}
