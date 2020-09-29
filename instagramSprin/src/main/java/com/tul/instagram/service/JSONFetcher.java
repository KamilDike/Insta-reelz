package com.tul.instagram.service;

import com.fasterxml.jackson.databind.JsonNode;
import com.jayway.jsonpath.JsonPath;
import com.tul.instagram.model.Video;
import net.minidev.json.JSONArray;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

public class JSONFetcher {

    private static String json;
    private static JsonNode node;

    public static String getEndCursor(String page) {
        if (page.equals("first")) {
            return node.get("graphql").get("user").get("edge_owner_to_timeline_media").get("page_info").get("end_cursor")
                    .asText().replace("=","");
        }
        else {
            return node.get("data").get("user").get("edge_owner_to_timeline_media").get("page_info").get("end_cursor")
                    .asText().replace("=","");
        }
    }

    public static String getID() {
        return node.get("graphql").get("user").get("id").asText();
    }

    public static String getProfilePic() {
        return node.get("graphql").get("user").get("profile_pic_url_hd").asText();
    }

    public static String getChannelName() {
        return node.get("graphql").get("user").get("username").asText();
    }

    public static List<String> getImages(String JSON) {
        List<String> out = new LinkedList<>();
        String[] urlsArr = JSON.split("\"display_url\":\"");
        for (int i = 0; i < urlsArr.length - 1; i++) {
            out.add(urlsArr[i + 1].split("\",\"")[0]);
        }
        return out.stream().distinct().collect(Collectors.toList());
    }

    public static List<Video> getVideosJsonPath() {
        List<Video> videos = new LinkedList<>();
        JSONArray read = JsonPath.read(json, "$[?(@.graphql)]");
        if (!read.isEmpty()) {
            JSONArray edges = JsonPath.read(json,
                    "$.graphql.user.edge_owner_to_timeline_media.edges[*].node");
            edges.forEach(edge -> {
                if (JsonPath.read(edge, "$.__typename").equals("GraphVideo")) {
                    videos.add(new Video(
                            JsonPath.read(edge, "$.video_url").toString(),
                            JsonPath.read(edge, "$.edge_media_to_caption.edges[0].node.text").toString()
                    ));
                }
            });
        }
        else {
            JSONArray edges = JsonPath.read(json,
                    "$.data.user.edge_owner_to_timeline_media.edges[*].node");
            edges.forEach(edge -> {
                if (JsonPath.read(edge, "$.__typename").equals("GraphVideo")) {
                    videos.add(new Video(
                            JsonPath.read(edge, "$.video_url").toString(),
                            JsonPath.read(edge, "$.edge_media_to_caption.edges[0].node.text").toString()
                    ));
                }
            });
        }
        return videos;
    }

    /**
     * URLConnection
     * @param URL Uniform Resource Locator.
     * @return JSON as output.
     */
    public static String readUrl(String URL) {
        String out = "";
        try {
            java.net.URL oracle = new URL(URL);
            URLConnection yc = oracle.openConnection();
            BufferedReader in = new BufferedReader(new InputStreamReader(
                    yc.getInputStream()));
            String inputLine;
            while ((inputLine = in.readLine()) != null) {
                out += inputLine;
            }
            node = Json.parse(out);
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println(out);
        json = out;
        getVideosJsonPath();
        return out;
    }
}
