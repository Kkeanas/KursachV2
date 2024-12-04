package com.project.kursachv2.Student;


import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class StudentDTO {
    @Pattern(regexp = "^[А-Я][а-я]+")
    private String stuFirstName;
    @Pattern(regexp = "^[А-Я][а-я]+")
    private String stuSecondName;
    @Pattern(regexp = "^[А-Я][а-я]+")
    private String stuSurName;
    private String stuAddress;
    @Email(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Почта введена неверно")
    private String stuEmail;
    @Pattern(regexp = "^\\+[0-9]{11}" ,message = "Номер должен быть введен в формате: +12223334455")
    private String stuPhone;
    @NotNull
    private String stuComment;
    @NotNull
    private String stuCommentFromStudent;

    public String getStuFirstName() {
        return stuFirstName;
    }

    public String getStuSecondName() {
        return stuSecondName;
    }

    public String getStuSurName() {
        return stuSurName;
    }

    public String getStuAddress() {
        return stuAddress;
    }

    public String getStuEmail() {
        return stuEmail;
    }

    public String getStuPhone() {
        return stuPhone;
    }

    public String getStuComment() {
        return stuComment;
    }

    public String getStuCommentFromStudent() {
        return stuCommentFromStudent;
    }
}
