package net.ddns.asadal.asadalrp.domain.library;

import net.ddns.asadal.asadalrp.domain.AbstractDomain;

import javax.persistence.Entity;

@Entity
public class Moodlet extends AbstractDomain {
    private int value;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
