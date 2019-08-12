package com.sanju.remoteapilibrary.core.service;

import com.sanju.remoteapilibrary.core.entity.YoutubeChannelInfo;

import java.util.List;

public interface YoutubeChannelService {

    void save(YoutubeChannelInfo channelInfo);
    void update(YoutubeChannelInfo channelInfo);
    YoutubeChannelInfo get(long id);
    YoutubeChannelInfo getByChannelId(String channelId);
    List<YoutubeChannelInfo> getAll();

}
