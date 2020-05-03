package net.ddns.asadal.asadalrp.domain;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.util.Set;

@Entity
public class Character {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotBlank(message = "name cannot be empty")
    @Size(max = 30, message = "max size is 30")
    private String name;
    @Size(max = 255, message = "max size is 255")
    private String descriptions;
    @Size(max = 255, message = "max size is 255")
    private String adminHint;
    @Enumerated
    private CharacterType characterType;

    @ManyToMany
    @JoinTable(
            name = "character_moodlets",
            joinColumns = {@JoinColumn(name = "character_id")},
            inverseJoinColumns = {@JoinColumn(name = "moodlet_id")}
    )
    private Set<Moodlet> moodlets;

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

    public Set<Moodlet> getMoodlets() {
        return moodlets;
    }

    public void setMoodlets(Set<Moodlet> moodlets) {
        this.moodlets = moodlets;
    }
}
