package net.ddns.asadal.asadalrp.domain.dto;

import net.ddns.asadal.asadalrp.domain.Moodlet;

public class MoodletDto {
    private String name;
    private String description;
    private int durability;
    private byte value;

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

    public int getDurability() {
        return durability;
    }

    public void setDurability(int durability) {
        this.durability = durability;
    }

    public byte getValue() {
        return value;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public Moodlet createMoodlet() {
        Moodlet moodlet = new Moodlet();

        moodlet.setName(name);
        moodlet.setDescription(description);
        moodlet.setDurability(durability);
        moodlet.setValue(value);
        return moodlet;
    }
}
