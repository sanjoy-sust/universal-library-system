package com.sanju.remoteapilibrary.core.service;

import com.sanju.remoteapilibrary.core.entity.YoutubeChannelInfo;
import com.sanju.remoteapilibrary.core.repository.YoutubeChannelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class YoutubeChannelServiceImpl implements YoutubeChannelService {

    @Autowired
    YoutubeChannelRepository youtubeChannelRepository;


    @Override
    public void save(YoutubeChannelInfo channelInfo) {
        youtubeChannelRepository.save(channelInfo);
    }

    @Override
    public void update(YoutubeChannelInfo channelInfo) {
        youtubeChannelRepository.save(channelInfo);
    }

    @Override
    public YoutubeChannelInfo get(long id) {
        return youtubeChannelRepository.getOne(id);
    }

    @Override
    public YoutubeChannelInfo getByChannelId(String channelId) {
        return youtubeChannelRepository.findByChannelId(channelId);
    }

    @Override
    public List<YoutubeChannelInfo> getAll() {
        return youtubeChannelRepository.findAll();
    }
}
