package com.sanju.remoteapilibrary.core.entity;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import java.util.Date;

@Entity(name = "youtube_video_info")
@Data
public class YouTubeVideoInfo extends BaseEntity{

    @Column(name = "video_id")
    private String videoId;

    @Column(name = "title")
    private String title;

    @Column(name = "thumbnail_url")
    private String thumbnailUrl;


    @Column(name = "description")
    private String description;

    @Column(name = "published_date")
    private Date publishedDate;

    @Column(name = "definition")
    private String videoDefinition;

    @Column(name = "duration")
    private String videoDuration;

    @Column(name = "caption")
    private String videoCaption;

    @Column(name = "projection")
    private String videoprojection;

    @Column(name = "country_restricted")
    private String countryRestricted;

    @Column(name = "channel_id")
    private YoutubeChannelInfo channelInfo;
}
