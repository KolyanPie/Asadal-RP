package net.ddns.asadal.asadalrp.domain.library;

import javax.persistence.Entity;

@Entity
public class Knowledge extends AbstractLibraryDomain {
    private String description;
    private String picture;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }
}
