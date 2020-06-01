package net.ddns.asadal.asadalrp.domain.library;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractPicturedLibraryDomain extends AbstractLibraryDomain {

    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
