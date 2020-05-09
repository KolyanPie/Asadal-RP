package net.ddns.asadal.asadalrp.domain.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class PersonDto {
    private Long id;
    private String name;
    private String description;
    private MultipartFile picture;
    private String adminHint;
    private boolean playable;

    private Set<Long> actions;
    private Set<Long> characters;

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

    public MultipartFile getPicture() {
        return picture;
    }

    public void setPicture(MultipartFile picture) {
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

    public Set<Long> getActions() {
        return actions;
    }

    public void setActions(Set<Long> actions) {
        this.actions = actions;
    }

    public Set<Long> getCharacters() {
        return characters;
    }

    public void setCharacters(Set<Long> characters) {
        this.characters = characters;
    }
}
