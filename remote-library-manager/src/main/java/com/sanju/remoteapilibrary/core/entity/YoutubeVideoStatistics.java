package com.sanju.remoteapilibrary.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Entity(name = "youtube_video_stat")
@Data
public class YoutubeVideoStatistics {

    @Column(name = "like_count")
    private long likeCount;

    @Column(name = "dislike_count")
    private long dislikeCount;

    @Column(name = "view_count")
    private long viewCount;

    @Column(name = "favourite_count")
    private long favouriteCount;

    @Column(name = "comment_count")
    private long commentCount;

    @Column(name = "video_id")
    private long videoId;
}
