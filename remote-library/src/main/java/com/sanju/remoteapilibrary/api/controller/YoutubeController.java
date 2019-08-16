package com.sanju.remoteapilibrary.api.controller;

import com.sanju.remoteapilibrary.core.entity.YouTubeVideoInfo;
import com.sanju.remoteapilibrary.core.entity.YoutubeChannelInfo;
import com.sanju.remoteapilibrary.core.entity.YoutubeVideoStatistics;
import com.sanju.remoteapilibrary.core.service.YoutubeApiService;
import com.sanju.remoteapilibrary.core.service.YoutubeChannelService;
import com.sanju.remoteapilibrary.core.service.YoutubeVideoInfoService;
import com.sanju.remoteapilibrary.core.service.YoutubeVideoStatService;
import com.sanju.remoteapilibrary.remote.YoutubeVideoListCrawler;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("youtube")
public class YoutubeController {

    @Autowired
    YoutubeApiService youtubeApiService;

    @Autowired
    YoutubeVideoInfoService youtubeVideoInfoService;

    @Autowired
    YoutubeChannelService youtubeChannelService;

    @Autowired
    YoutubeVideoStatService youtubeVideoStatService;

    @GetMapping(value = "crawl/{keyword}/{pageToCrawl}")
    public String crawlVideo(@PathVariable String keyword, @PathVariable long pageToCrawl) {
        return youtubeApiService.crawlYoutubeVideoInfo(keyword,pageToCrawl);
    }

    @GetMapping
    public List<YouTubeVideoInfo> getAll(){
        return youtubeVideoInfoService.getAll();
    }

    @GetMapping(value = "{id}")
    public YouTubeVideoInfo getOne(@PathVariable long id){
        return youtubeVideoInfoService.get(id);
    }

    @GetMapping(value = "channel")
    public List<YoutubeChannelInfo> getAllChannel(){
        return youtubeChannelService.getAll();
    }

    @GetMapping(value = "channel/{id}")
    public YoutubeChannelInfo getChannel(@PathVariable long id){
        return youtubeChannelService.get(id);
    }

    @GetMapping(value = "stat")
    public List<YoutubeVideoStatistics> getAllstat(){
        return youtubeVideoStatService.getAll();
    }

    @GetMapping(value = "stat/{id}")
    public YoutubeVideoStatistics getStats(@PathVariable long id){
        return youtubeVideoStatService.get(id);
    }

/*    @PutMapping(value = "{id}")
    public void update(@PathVariable long id, @RequestBody YouTubeVideoInfo youTubeVideoInfo){
         youtubeVideoInfoService.update(youTubeVideoInfo);
    }

    @PostMapping
    public void create(@PathVariable long id, @RequestBody YouTubeVideoInfo youTubeVideoInfo){
        youtubeVideoInfoService.update(youTubeVideoInfo);
    }*/


}
