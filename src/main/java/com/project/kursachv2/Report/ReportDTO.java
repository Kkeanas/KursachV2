package com.project.kursachv2.Report;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class ReportDTO {
    private long group_id;
    @NotNull
    @Pattern(regexp = "^[А-Я][а-я]+", message="")
    private String stuFirstName;
    @NotNull
    @Pattern(regexp = "^[А-Я][а-я]+")
    private String stuSecondName;
    @NotNull
    @Pattern(regexp = "^[А-Я][а-я]+")
    private String stuSurName;

    public long getGroup_id() {
        return group_id;
    }

    public String getStuFirstName() {
        return stuFirstName;
    }

    public String getStuSecondName() {
        return stuSecondName;
    }

    public String getStuSurName() {
        return stuSurName;
    }

    public void setGroup_id(long group_id) {
        this.group_id = group_id;
    }

    public void setStuFirstName(String stuFirstName) {
        this.stuFirstName = stuFirstName;
    }

    public void setStuSecondName(String stuSecondName) {
        this.stuSecondName = stuSecondName;
    }

    public void setStuSurName(String stuSurName) {
        this.stuSurName = stuSurName;
    }
}
