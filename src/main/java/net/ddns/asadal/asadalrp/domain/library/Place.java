package net.ddns.asadal.asadalrp.domain.library;

import net.ddns.asadal.asadalrp.domain.AbstractPicturedDomain;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Place extends AbstractPicturedDomain {

    private String description;

    @ManyToMany
    @JoinTable(
            name = "place_moodlets",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "moodlet_id")}
    )
    private Set<Moodlet> moodlets;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<Moodlet> getMoodlets() {
        return moodlets;
    }

    public void setMoodlets(Set<Moodlet> moodlets) {
        this.moodlets = moodlets;
    }
}
