/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.log;

import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;

import java.util.List;

public class PlaylistLog {
    public void printAllPlaylists(List<Playlist> playlistList) {
        System.out.println("\n==============================");
        System.out.println("Showing available playlists");
        System.out.println("================================");
        System.out.println("Id       Name of playlist");
        for (int i = 0; i < playlistList.size(); i++) {
            System.out.println((i + 1) + "         " + playlistList.get(i).getName());
        }
    }

    public void printAllSongsInPlaylist(List<Song> songList) {
        System.out.println("\nSongs in the Selected Playlist");
        System.out.println("================================");
        System.out.printf("%-10s%-30s%-30s%-20s%n", "Id", "Name", "Artist", "Genre");
        for (Song song : songList) {
            System.out.printf("%-9d%-30s%-30s%-20s%n", song.getId(), song.getName(), song.getArtistName(), song.getGenre());
        }
    }

    public void addPlaylistFeedback(int result) {
        System.out.println();
        if (result > 0) {
            System.out.println("\u001B[32mPlaylist created successfully !\u001b[0m");
        } else {
            System.out.println("\u001b[31mCouldn't create playlist !\u001b[0m");
        }
    }

    public void addSongFeedback(int result) {
        System.out.println();
        if (result > 0) {
            System.out.println("\u001B[32mSong added successfully !\u001b[0m");
        } else {
            System.out.println("\u001b[31mCouldn't add song !\u001b[0m");
        }
    }

    public void removePlaylistFeedback(int result) {
        System.out.println();
        if (result > 0) {
            System.out.println("\u001B[32mPlaylist removed successfully !\u001b[0m");
        } else {
            System.out.println("\u001b[31mCouldn't remove playlist !\u001b[0m");
        }
    }
}
