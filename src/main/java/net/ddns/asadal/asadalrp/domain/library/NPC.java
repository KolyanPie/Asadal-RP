package net.ddns.asadal.asadalrp.domain.library;

import javax.persistence.Entity;

@Entity
public class NPC extends AbstractPicturedLibraryDomain {

    private String description;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
