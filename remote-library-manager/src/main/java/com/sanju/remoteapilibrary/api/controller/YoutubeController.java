package com.sanju.remoteapilibrary.api.controller;

import com.sanju.remoteapilibrary.remote.YoutubeVideoListCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("youtube")
public class YoutubeController {

    @Autowired
    YoutubeVideoListCrawler youtubeVideoListCrawler;

    @GetMapping
    public List<Object> getVideoList() {
        return youtubeVideoListCrawler.getYoutubeVideoList("BCS");
    }


}
