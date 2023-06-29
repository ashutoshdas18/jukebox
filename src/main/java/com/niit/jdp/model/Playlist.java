/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.model;

import java.util.List;

public class Playlist {
    private int id;
    private String name;
    private List<String> songId;
    private String userId;

    public Playlist() {

    }

    public Playlist(int id, String name, List<String> songId, String userId) {
        this.id = id;
        this.name = name;
        this.songId = songId;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getSongId() {
        return songId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "Playlist{" + "id=" + id + ", name='" + name + '\'' + ", songId=" + songId + ", userId='" + userId + '\'' + '}';
    }
}