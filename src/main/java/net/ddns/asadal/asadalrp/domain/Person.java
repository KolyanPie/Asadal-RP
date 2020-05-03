package net.ddns.asadal.asadalrp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "name cannot be empty")
    @Size(max = 30, message = "max size is 30")
    private String name;
    @Size(max = 255, message = "max size is 255")
    private String description;
    private String picture;
    @Size(max = 255, message = "max size is 255")
    private String adminHint;
    private boolean playable;

    @ManyToMany
    @JoinTable(
            name = "person_actions",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "action_id")}
    )
    private Set<Action> actions;

    @ManyToMany
    @JoinTable(
            name = "person_characters",
            joinColumns = {@JoinColumn(name = "person_id")},
            inverseJoinColumns = {@JoinColumn(name = "character_id")}
    )
    private Set<Character> characters;

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

    public boolean isPlayable() {
        return playable;
    }

    public void setPlayable(boolean playable) {
        this.playable = playable;
    }

    public Set<Action> getActions() {
        return actions;
    }

    public void setActions(Set<Action> actions) {
        this.actions = actions;
    }

    public Set<Character> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Character> characters) {
        this.characters = characters;
    }
}
