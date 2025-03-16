package net.jorjai.bummer4.domain;

import java.util.List;

public class TagResponse {
    private List<Tag> versatile;
    private List<Tag> nsfw;

    public List<Tag> getVersatile() {
        return versatile;
    }

    public List<Tag> getNsfw() {
        return nsfw;
    }
}