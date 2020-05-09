package net.ddns.asadal.asadalrp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Place {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "name cannot be empty")
    @Size(max = 30, message = "max size is 30")
    private String name;
    @Size(max = 1024, message = "max size is 1024")
    private String description;
    private String picture;
    @Size(max = 255, message = "max size is 255")
    private String adminHint;

    @ManyToMany
    @JoinTable(
            name = "place_moodlets",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "moodlet_id")}
    )
    private Set<Moodlet> moodlets;

    @ManyToMany
    @JoinTable(
            name = "place_actions",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "action_id")}
    )
    private Set<Action> actions;

    @ManyToMany
    @JoinTable(
            name = "place_persons",
            joinColumns = {@JoinColumn(name = "place_id")},
            inverseJoinColumns = {@JoinColumn(name = "person_id")}
    )
    private Set<Person> persons;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

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

    public String getAdminHint() {
        return adminHint;
    }

    public void setAdminHint(String adminHint) {
        this.adminHint = adminHint;
    }

    public Set<Moodlet> getMoodlets() {
        return moodlets;
    }

    public void setMoodlets(Set<Moodlet> moodlets) {
        this.moodlets = moodlets;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Person> getPersons() {
        return persons;
    }

    public void setPersons(Set<Person> persons) {
        this.persons = persons;
    }
}
