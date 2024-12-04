package com.project.kursachv2.Organization;

import com.project.kursachv2.Student.Student;
import com.project.kursachv2.Student.StudentDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrganizationService {
    @Autowired
    private OrganizationRepository organizationRepository;

    public boolean organizationExitsById(long id) {
        return organizationRepository.existsById(id);
    }

    public Organization getOrganizationById(long id) {
        return organizationRepository.getReferenceById(id);
    }

    public List<Organization> getAllOrganizations() {
        return organizationRepository.findAll();
    }

    public Organization addOrganization(Organization organization) {
        return organizationRepository.save(organization);
    }

    public Organization updateOrganizationById(Organization organization, long id) {
        if (organizationExitsById(id)) {
            organization.setId(id);
            return organizationRepository.save(organization);
        } else {
            throw new DataIntegrityViolationException("Организации с id="+id+" не существует");
        }
    }

    public void deleteOrganizationById(long id) {
        if (organizationExitsById(id)) {
            organizationRepository.deleteById(id);
        } else {
            throw new DataIntegrityViolationException("Организации с id="+id+" не существует");
        }
    }

    public Organization convertDTOtoOrganization(OrganizationDTO organizationDTO) {
        Organization organization = new Organization();
        organization.setOrgName(organizationDTO.getOrgName());
        organization.setOrgAddress(organizationDTO.getOrgAddress());
        organization.setOrgPhone(organizationDTO.getOrgPhone());
        organization.setOrgEmail(organizationDTO.getOrgEmail());
        organization.setOrgContactName(organizationDTO.getOrgContactName());
        organization.setOrgRequirements(organizationDTO.getOrgRequirements());
        return organization;
    }
}
