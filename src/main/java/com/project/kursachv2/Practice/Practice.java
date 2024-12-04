package com.project.kursachv2.Practice;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.project.kursachv2.Report.Report;
import jakarta.persistence.*;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Date;

@Entity
@Table(name = "practice")
public class Practice {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private boolean prcIsPayed = false;
    private String prcRelevance = "Не указан";
    private String prcMaterial = "Не указан";
    private String prcDescription = "Не указан";
    private String prcDirector = "Не указан";
    private Date prcStarting = new Date();
    private Date prcEnding = new Date();
    private boolean prcFutureWork = false;
    private boolean prcNextYear = false;
    private boolean prcReportPassed = false;
    private String prcPosition = "Не указан";
    private String prcWork = "Не указан";
    @JsonBackReference
    @OneToOne(mappedBy = "practice")
    private Report report;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public boolean isPrcIsPayed() {
        return prcIsPayed;
    }

    public void setPrcIsPayed(boolean prcIsPayed) {
        this.prcIsPayed = prcIsPayed;
    }

    public String getPrcRelevance() {
        return prcRelevance;
    }

    public void setPrcRelevance(String prcRelevance) {
        this.prcRelevance = prcRelevance;
    }

    public String getPrcMaterial() {
        return prcMaterial;
    }

    public void setPrcMaterial(String prcMaterial) {
        this.prcMaterial = prcMaterial;
    }

    public String getPrcDescription() {
        return prcDescription;
    }

    public void setPrcDescription(String prcDescription) {
        this.prcDescription = prcDescription;
    }

    public String getPrcDirector() {
        return prcDirector;
    }

    public void setPrcDirector(String prcDirector) {
        this.prcDirector = prcDirector;
    }

    public Date getPrcStarting() {
        return prcStarting;
    }

    public void setPrcStarting(Date prcStarting) {
        this.prcStarting = prcStarting;
    }

    public Date getPrcEnding() {
        return prcEnding;
    }

    public void setPrcEnding(Date prcEnding) {
        this.prcEnding = prcEnding;
    }

    public boolean isPrcFutureWork() {
        return prcFutureWork;
    }

    public void setPrcFutureWork(boolean prcFutureWork) {
        this.prcFutureWork = prcFutureWork;
    }

    public boolean isPrcNextYear() {
        return prcNextYear;
    }

    public void setPrcNextYear(boolean prcNextYear) {
        this.prcNextYear = prcNextYear;
    }

    public boolean isPrcReportPassed() {
        return prcReportPassed;
    }

    public void setPrcReportPassed(boolean prcReportPassed) {
        this.prcReportPassed = prcReportPassed;
    }

    public String getPrcPosition() {
        return prcPosition;
    }

    public void setPrcPosition(String prcPosition) {
        this.prcPosition = prcPosition;
    }

    public String getPrcWork() {
        return prcWork;
    }

    public void setPrcWork(String prcWork) {
        this.prcWork = prcWork;
    }

}

@Repository
interface PracticeRepository extends JpaRepository<Practice, Long> {}
