package com.project.kursachv2.Report;

import com.project.kursachv2.Practice.Practice;
import com.project.kursachv2.Practice.PracticeDTO;
import com.project.kursachv2.Student.Student;
import com.project.kursachv2.Student.StudentDTO;

public class NewReport {
    private long id;
    private StudentDTO student;
    private long organization_id;
    private PracticeDTO practice;

    public StudentDTO getStudent() {
        return student;
    }

    public long getOrganization_id() {
        return organization_id;
    }

    public PracticeDTO getPractice() {
        return practice;
    }

    public long getId() {
        return id;
    }
}
