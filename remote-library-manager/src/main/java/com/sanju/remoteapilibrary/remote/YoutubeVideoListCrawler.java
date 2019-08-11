package com.sanju.remoteapilibrary.remote;

import com.google.api.client.googleapis.json.GoogleJsonResponseException;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestInitializer;
import com.google.api.services.youtube.YouTube;
import com.google.api.services.youtube.YouTubeRequestInitializer;
import com.google.api.services.youtube.model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Service;

import javax.print.DocFlavor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

@Service
public class YoutubeVideoListCrawler {

    @Autowired
    private Environment env;

    private static final long NUMBER_OF_VIDEOS_RETURNED = 50;

    /**
     * Define a global instance of a Youtube object, which will be used
     * to make YouTube Data API requests.
     */
    private YouTube youtube;

    /**
     * Initialize a YouTube object to search for videos on YouTube. Then
     * display the name and thumbnail image of each video in the result set.
     */
    public List<Object> getYoutubeVideoList(String queryTerm) {

        try {
            // This object is used to make YouTube Data API requests. The last
            // argument is required, but since we don't need anything
            // initialized when the HttpRequest is initialized, we override
            // the interface and provide a no-op function.
            youtube = new YouTube.Builder(Auth.HTTP_TRANSPORT, Auth.JSON_FACTORY, new HttpRequestInitializer() {
                public void initialize(HttpRequest request) throws IOException {
                }
            }).setApplicationName("YoutubeVideoInfo")
                    .setYouTubeRequestInitializer(new YouTubeRequestInitializer(env.getProperty("youtube.apikey"))).build();

            // Define the API request for retrieving search results.
            YouTube.Search.List search = youtube.search().list("id,snippet");

            String apiKey = env.getProperty("youtube.apikey");
          //  search.setKey(apiKey);
            search.setQ(queryTerm);

            System.out.println("Key " + apiKey);
            System.out.println("Query " + queryTerm);

            // Restrict the search results to only include videos. See:
            // https://developers.google.com/youtube/v3/docs/search/list#type
            search.setType("video");

            // To increase efficiency, only retrieve the fields that the
            // application uses.
            //search.setFields("items(id/kind,id/videoId,snippet/title,snippet/thumbnails/default/url)");
            search.setMaxResults(NUMBER_OF_VIDEOS_RETURNED);

            // Call the API and print results.
            SearchListResponse searchResponse = search.execute();


            List<SearchResult> searchResultList = searchResponse.getItems();
            if (searchResultList != null) {
                prettyPrint(searchResultList.iterator(), queryTerm);
            }

            System.out.println("Next Page token : " + searchResponse.getNextPageToken());
        } catch (GoogleJsonResponseException e) {
            System.err.println("There was a service error: " + e.getDetails().getCode() + " : "
                    + e.getDetails().getMessage());
        } catch (IOException e) {
            System.err.println("There was an IO error: " + e.getCause() + " : " + e.getMessage());
        } catch (Throwable t) {
            t.printStackTrace();
        }
        return null;
    }

    /*
     * Prints out all results in the Iterator. For each result, print the
     * title, video ID, and thumbnail.
     *
     * @param iteratorSearchResults Iterator of SearchResults to print
     *
     * @param query Search query (String)
     */
    private void prettyPrint(Iterator<SearchResult> iteratorSearchResults, String query) throws IOException {

        System.out.println("\n=============================================================");
        System.out.println(
                "   First " + NUMBER_OF_VIDEOS_RETURNED + " videos for search on \"" + query + "\".");
        System.out.println("=============================================================\n");

        if (!iteratorSearchResults.hasNext()) {
            System.out.println(" There aren't any results for your query.");
        }

        while (iteratorSearchResults.hasNext()) {

            SearchResult singleVideo = iteratorSearchResults.next();
            System.out.println("\n----------------------------video details---------------------------------\n");
            System.out.println("Description : " + singleVideo.getSnippet().getDescription());
            System.out.println("Published : " + singleVideo.getSnippet().getPublishedAt());

            getChannelDetailsById(singleVideo.getSnippet().getChannelId());

            ResourceId rId = singleVideo.getId();

            // Confirm that the result represents a video. Otherwise, the
            // item will not contain a video ID.
            if (rId.getKind().equals("youtube#video")) {
                Thumbnail thumbnail = singleVideo.getSnippet().getThumbnails().getDefault();

                System.out.println(" Video Id " + rId.getVideoId());
                System.out.println(" Title: " + singleVideo.getSnippet().getTitle());
                System.out.println(" Thumbnail: " + thumbnail.getUrl());
                System.out.println(" Kind : " + rId.getKind());
                System.out.println("\n----------------------Video statistics---------------------------------------\n");


                getVideosStatistics(rId.getVideoId());
            }


        }
    }

    private void getChannelDetailsById(String channelId) throws IOException {
        YouTube.Channels.List channels = youtube.channels().list("snippet, statistics");
        channels.setId(channelId);
        ChannelListResponse channelResponse = channels.execute();
        for (Channel c : channelResponse.getItems()) {
            System.out.println("Name: " + c.getSnippet().getTitle());
            System.out.println("Subs: " + c.getStatistics().getSubscriberCount());
            System.out.println("channel Playlist favorites : "+c.getContentDetails().getRelatedPlaylists().getFavorites());

        }
    }


    public Object getVideosStatistics(String id) throws IOException {
        YouTube.Videos.List list = youtube.videos().list("statistics,contentDetails");
        list.setId(id);
       // list.setKey(env.getProperty("youtube.apikey"));
        Video v = list.execute().getItems().get(0);
        System.out.println("The view count is: " + v.getStatistics().getViewCount());
        System.out.println("The like count is : " + v.getStatistics().getLikeCount());
        System.out.println("The dislike count is : " + v.getStatistics().getDislikeCount());
        System.out.println("The comment count is : " + v.getStatistics().getCommentCount());
        System.out.println("The favorite count is : " + v.getStatistics().getFavoriteCount());

        System.out.println("The definition  is : " + v.getContentDetails().getDefinition());
        System.out.println("The duration  is : " + v.getContentDetails().getDuration());
        System.out.println("The caption is : " + v.getContentDetails().getCaption());
        System.out.println("The content rating is : " + v.getContentDetails().getContentRating());
        System.out.println("The projecttion is : " + v.getContentDetails().getProjection());
        System.out.println("The countryRestricted is : " + v.getContentDetails().getCountryRestriction());

        return null;
    }


    public Object getChannelDetails(String name) throws IOException {
        YouTube.Search.List search = youtube.search().list("snippet");
        search.setQ(name);
        search.setType("channel");
        String apiKey = env.getProperty("youtube.apikey");
      //  search.setKey(apiKey);

        SearchListResponse searchResponse = search.execute();
        List<SearchResult> searchResultList = searchResponse.getItems();
        if (searchResultList != null) {
            for (SearchResult searchResult : searchResultList) {
                String channelId = searchResult.getSnippet().getChannelId();

                getChannelDetailsById(channelId);
            }

        }
        return null;
    }
}
