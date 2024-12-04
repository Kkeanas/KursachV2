package com.project.kursachv2.Organization;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.kursachv2.Report.Report;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "organizaiton")
public class Organization {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String orgName;
    private String orgAddress;
    private String orgPhone;
    private String orgEmail;
    private String orgContactName;
    private String orgRequirements;
    @JsonBackReference
    @OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
    private List<Report> reports = new ArrayList<>();

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getOrgName() {
        return orgName;
    }

    public void setOrgName(String orgName) {
        this.orgName = orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public void setOrgAddress(String orgAddress) {
        this.orgAddress = orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public void setOrgPhone(String orgPhone) {
        this.orgPhone = orgPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public void setOrgEmail(String orgEmail) {
        this.orgEmail = orgEmail;
    }

    public String getOrgContactName() {
        return orgContactName;
    }

    public void setOrgContactName(String orgContactName) {
        this.orgContactName = orgContactName;
    }

    public String getOrgRequirements() {
        return orgRequirements;
    }

    public void setOrgRequirements(String orgRequirements) {
        this.orgRequirements = orgRequirements;
    }
}

@Repository
interface OrganizationRepository extends JpaRepository<Organization, Long> {}
