package com.sanju.is.core.entity;

import lombok.Data;

import java.util.List;

@Data
public class Gallery {
    private int id;
    private List<Object> images;

    public Gallery() {
    }

    public Gallery(int galleryId) {
        this.id = galleryId;
    }
}
