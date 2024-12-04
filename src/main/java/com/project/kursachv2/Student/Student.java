package com.project.kursachv2.Student;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.kursachv2.Report.Report;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Entity
@Table(name="student")
public class Student {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String stuFirstName;
    private String stuSecondName;
    private String stuSurName;
    private String stuAddress = "Не указан";
    private String stuEmail = "Не указан";
    private String stuPhone = "Не указан";
    private String stuComment = "Не указан";
    private String stuCommentFromStudent = "Не указан";
    @JsonBackReference
    @OneToOne(mappedBy = "student")
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getStuFirstName() {
        return stuFirstName;
    }

    public void setStuFirstName(String stuFirstName) {
        this.stuFirstName = stuFirstName;
    }

    public String getStuSecondName() {
        return stuSecondName;
    }

    public void setStuSecondName(String stuSecondName) {
        this.stuSecondName = stuSecondName;
    }

    public String getStuSurName() {
        return stuSurName;
    }

    public void setStuSurName(String stuSurName) {
        this.stuSurName = stuSurName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public void setStuAddress(String stuAddress) {
        this.stuAddress = stuAddress;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public void setStuEmail(String stuEmail) {
        this.stuEmail = stuEmail;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public void setStuPhone(String stuPhone) {
        this.stuPhone = stuPhone;
    }

    public String getStuComment() {
        return stuComment;
    }

    public void setStuComment(String stuComment) {
        this.stuComment = stuComment;
    }

    public String getStuCommentFromStudent() {
        return stuCommentFromStudent;
    }

    public void setStuCommentFromStudent(String stuCommentFromStudent) {
        this.stuCommentFromStudent = stuCommentFromStudent;
    }

    public Report getReport() {
        return report;
    }

    public void setReport(Report report) {
        this.report = report;
    }
}

@Repository
interface StudentRepository extends JpaRepository<Student, Long> {}
