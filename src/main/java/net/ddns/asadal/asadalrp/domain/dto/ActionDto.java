package net.ddns.asadal.asadalrp.domain.dto;

import net.ddns.asadal.asadalrp.domain.Action;

public class ActionDto {
    private String name;
    private String adminHint;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAdminHint() {
        return adminHint;
    }

    public void setAdminHint(String adminHint) {
        this.adminHint = adminHint;
    }

    public Action createAction() {
        Action action = new Action();

        action.setName(name);
        action.setAdminHint(adminHint);
        return action;
    }
}
