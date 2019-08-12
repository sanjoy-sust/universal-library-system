package com.sanju.remoteapilibrary.core.service;

import com.sanju.remoteapilibrary.core.entity.YouTubeVideoInfo;
import com.sanju.remoteapilibrary.core.entity.YoutubeVideoStatistics;

import java.util.List;

public interface YoutubeVideoStatService {
    void save(YoutubeVideoStatistics videoStatistics);
    void update(YoutubeVideoStatistics videoInfo);
    YoutubeVideoStatistics get(long id);
    List<YoutubeVideoStatistics> getAll();
}
