package com.sanju.remoteapilibrary.core.repository;

import com.sanju.remoteapilibrary.core.entity.YouTubeVideoInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface YoutubeVideoInfoRepository extends JpaRepository<YouTubeVideoInfo,Long> {
    YouTubeVideoInfo findByVideoId(String videoId);
}
