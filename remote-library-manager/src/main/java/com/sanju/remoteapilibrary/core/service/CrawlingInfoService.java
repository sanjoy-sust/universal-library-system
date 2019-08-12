package com.sanju.remoteapilibrary.core.service;

import com.sanju.remoteapilibrary.core.entity.CrawlingInfo;
import com.sanju.remoteapilibrary.core.entity.YoutubeChannelInfo;

import java.util.List;

public interface CrawlingInfoService {
    void save(CrawlingInfo crawlingInfo);
    void update(CrawlingInfo crawlingInfo);
    CrawlingInfo get(long id);
    CrawlingInfo getBySearchKey(String searchKey);
    List<CrawlingInfo> getAll();
}
