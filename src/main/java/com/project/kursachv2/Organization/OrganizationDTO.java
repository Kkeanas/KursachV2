package com.project.kursachv2.Organization;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;

public class OrganizationDTO {
    @NotEmpty
    private String orgName;
    @NotEmpty
    private String orgAddress;
    @Pattern(regexp = "^\\+[0-9]{11}" ,message = "Номер должен быть введен в формате: +12223334455")
    private String orgPhone;
    @Email(regexp = "\\b[\\w.%-]+@[-.\\w]+\\.[A-Za-z]{2,4}\\b", message = "Почта введена неверно")
    private String orgEmail;
    @NotEmpty
    private String orgContactName;
    @NotEmpty
    private String orgRequirements;

    public String getOrgName() {
        return orgName;
    }

    public String getOrgAddress() {
        return orgAddress;
    }

    public String getOrgPhone() {
        return orgPhone;
    }

    public String getOrgEmail() {
        return orgEmail;
    }

    public String getOrgContactName() {
        return orgContactName;
    }

    public String getOrgRequirements() {
        return orgRequirements;
    }
}
