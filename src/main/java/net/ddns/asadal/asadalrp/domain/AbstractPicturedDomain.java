package net.ddns.asadal.asadalrp.domain;

import javax.persistence.MappedSuperclass;

@MappedSuperclass
public abstract class AbstractPicturedDomain extends AbstractDomain {

    private String picturePath;

    public String getPicturePath() {
        return picturePath;
    }

    public void setPicturePath(String picturePath) {
        this.picturePath = picturePath;
    }
}
