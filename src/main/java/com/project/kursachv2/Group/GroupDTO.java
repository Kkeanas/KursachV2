package com.project.kursachv2.Group;

import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public class GroupDTO {
    @NotNull
    @Pattern(regexp = "^[А-Я]{1,3}-[0-9]{2}(-[0-9])?", message = "Название группы неккоректно")
    private String grpName;

    public String getGrpName() {
        return grpName;
    }

    public void setGrpName(String grpName) {
        this.grpName = grpName;
    }
}
