package net.ddns.asadal.asadalrp.domain.library;

import javax.persistence.Entity;

@Entity
public class Moodlet extends AbstractLibraryDomain {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
