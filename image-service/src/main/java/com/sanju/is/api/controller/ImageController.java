package com.sanju.is.api.controller;

import com.sanju.is.core.entity.ImageEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.List;

@RestController
public class ImageController {
    @Autowired
    private Environment env;

    @RequestMapping("/images")
    public List<ImageEntity> getImages() {
        List<ImageEntity> images = Arrays.asList(
                new ImageEntity(1, "Treehouse of Horror V", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3842005760"),
                new ImageEntity(2, "The Town", "https://www.imdb.com/title/tt0096697/mediaviewer/rm3698134272"),
                new ImageEntity(3, "The Last Traction Hero", "https://www.imdb.com/title/tt0096697/mediaviewer/rm1445594112"));
        return images;
    }

}
