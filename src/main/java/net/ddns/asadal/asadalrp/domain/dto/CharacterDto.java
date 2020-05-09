package net.ddns.asadal.asadalrp.domain.dto;

import net.ddns.asadal.asadalrp.domain.CharacterType;

import java.util.Set;

public class CharacterDto {
    private Long id;
    private String name;
    private String descriptions;
    private String adminHint;
    private CharacterType characterType;

    private Set<Long> moodlets;

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

    public String getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(String descriptions) {
        this.descriptions = descriptions;
    }

    public String getAdminHint() {
        return adminHint;
    }

    public void setAdminHint(String adminHint) {
        this.adminHint = adminHint;
    }

    public CharacterType getCharacterType() {
        return characterType;
    }

    public void setCharacterType(CharacterType characterType) {
        this.characterType = characterType;
    }

    public Set<Long> getMoodlets() {
        return moodlets;
    }

    public void setMoodlets(Set<Long> moodlets) {
        this.moodlets = moodlets;
    }
}
