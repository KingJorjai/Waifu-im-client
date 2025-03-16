package net.jorjai.bummer4.domain;

import lombok.Getter;

import java.util.List;

@Getter
public class Image {
    private Artist artist;
    private int byte_size;
    private String dominant_color;
    private String extension;
    private int favorites;
    private int height;
    private int image_id;
    private boolean is_nsfw;
    private String liked_at;
    private String preview_url;
    private String signature;
    private String source;
    private List<Tag> tags;
    private String uploaded_at;
    private String url;
    private int width;
}
