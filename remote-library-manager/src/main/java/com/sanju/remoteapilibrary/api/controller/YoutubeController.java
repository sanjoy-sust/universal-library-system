package com.sanju.remoteapilibrary.api.controller;

import com.sanju.remoteapilibrary.core.service.YoutubeApiService;
import com.sanju.remoteapilibrary.remote.YoutubeVideoListCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("youtube")
public class YoutubeController {

    @Autowired
    YoutubeApiService youtubeApiService;

    @GetMapping(value = "crawl/{keyword}/{pageToCrawl}")
    public String crawlVideo(@PathVariable String keyword, @PathVariable long pageToCrawl) {
        return youtubeApiService.crawlYoutubeVideoInfo(keyword,pageToCrawl);
    }


}
