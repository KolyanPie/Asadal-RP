package net.ddns.asadal.asadalrp.domain.library;

import net.ddns.asadal.asadalrp.domain.AbstractPicturedDomain;

import javax.persistence.Entity;

@Entity
public class Knowledge extends AbstractPicturedDomain {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
