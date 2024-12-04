package com.project.kursachv2.Report;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.project.kursachv2.Group.Group;
import com.project.kursachv2.Organization.Organization;
import com.project.kursachv2.Practice.Practice;
import com.project.kursachv2.Student.Student;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="report")
public class Report {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student_id", unique = true)
    private Student student;
    @JsonManagedReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "organization_id")
    private Organization organization;
    @JsonManagedReference
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "practice_id", unique = true)
    private Practice practice;
    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "group_id")
    private Group group;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public Organization getOrganization() {
        return organization;
    }

    public void setOrganization(Organization organization) {
        this.organization = organization;
    }

    public Practice getPractice() {
        return practice;
    }

    public void setPractice(Practice practice) {
        this.practice = practice;
    }
    @JsonIgnore
    public Group getGroup() {
        return group;
    }

    public String getGrpName() {
        return group.getGrpName();
    }
    @JsonProperty("group_id")
    public long getGroupId() {
        return group.getId();
    }

    public void setGroup(Group group) {
        this.group = group;
    }
    @JsonIgnore
    public List<String> getAllinfo() {
        List<String> list = new ArrayList<>();
        list.add(getGrpName());
        list.add(getStudent().getStuFirstName());
        list.add(getStudent().getStuSecondName());
        list.add(getStudent().getStuSurName());
        list.add(getStudent().getStuAddress());
        list.add(getStudent().getStuPhone());
        list.add(getStudent().getStuComment());
        list.add(getStudent().getStuCommentFromStudent());
        if (organization != null) {
        list.add(getOrganization().getOrgName());
        list.add(getOrganization().getOrgAddress());
        list.add(getOrganization().getOrgPhone());
        list.add(getOrganization().getOrgEmail());
        list.add(getOrganization().getOrgContactName());
        list.add(getOrganization().getOrgRequirements());
        }
        else {
            list.add("Не указано");
            list.add("Не указано");
            list.add("Не указано");
            list.add("Не указано");
            list.add("Не указано");
        }
        list.add(Boolean.toString(getPractice().isPrcIsPayed()));
        list.add(getPractice().getPrcRelevance());
        list.add(getPractice().getPrcMaterial());
        list.add(getPractice().getPrcDescription());
        list.add(getPractice().getPrcDirector());
        list.add(getPractice().getPrcStarting().toString());
        list.add(getPractice().getPrcEnding().toString());
        list.add(Boolean.toString(getPractice().isPrcFutureWork()));
        list.add(Boolean.toString(getPractice().isPrcNextYear()));
        list.add(Boolean.toString(getPractice().isPrcReportPassed()));
        list.add(getPractice().getPrcPosition());
        list.add(getPractice().getPrcWork());
        return list;
    }

}

@Repository
interface ReportRepository extends JpaRepository<Report, Long> {
    List<Report> findAllByGroup(Group group);
    List<Report> findAllByOrganization(Organization organization);
}
