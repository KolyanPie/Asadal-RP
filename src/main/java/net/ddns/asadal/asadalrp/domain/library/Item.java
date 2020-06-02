package net.ddns.asadal.asadalrp.domain.library;

import net.ddns.asadal.asadalrp.domain.AbstractPicturedDomain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Item extends AbstractPicturedDomain {

    @ManyToMany
    @JoinTable(
            name = "item_moodlets",
            joinColumns = {@JoinColumn(name = "item_id")},
            inverseJoinColumns = {@JoinColumn(name = "moodlet_id")}
    )
    private Set<Moodlet> moodlets;

    public Set<Moodlet> getMoodlets() {
        return moodlets;
    }

    public void setMoodlets(Set<Moodlet> moodlets) {
        this.moodlets = moodlets;
    }
}
