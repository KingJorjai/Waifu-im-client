package net.jorjai.bummer4.domain;

import lombok.Getter;

@Getter
public class Tag {
    private int tagId;
    private String name;
    private String description;
    private boolean isNsfw;

    @Override
    public String toString() {
        return name;
    }
}