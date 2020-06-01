package net.ddns.asadal.asadalrp.domain.library;

import net.ddns.asadal.asadalrp.domain.Moodlet;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import java.util.Set;

@Entity
public class Item extends AbstractLibraryDomain {
    private String description;

    @ManyToMany
    @JoinTable(
            name = "item_moodlets",
            joinColumns = {@JoinColumn(name = "item_id")},
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
