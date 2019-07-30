package com.sanju.is.core.entity;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ImageEntity {
    private int id;
    private String name;
    private String url;
}
