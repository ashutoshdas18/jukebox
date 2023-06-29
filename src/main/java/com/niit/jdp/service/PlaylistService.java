/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.service;

import com.niit.jdp.exception.NoDataFoundException;
import com.niit.jdp.log.PlaylistLog;
import com.niit.jdp.log.SongMenu;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.PlaylistRepo;
import com.niit.jdp.repository.SongRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlaylistService {
    Scanner scanner = new Scanner(System.in);
    PlaylistLog playlistLog = new PlaylistLog();
    private PlaylistRepo playlistRepo;

    /**
     * This function prints all the playlists of a user
     *
     * @param id The id of the user whose playlists you want to get.
     */
    public void getPlayLists(String id) {
        try {
            playlistLog.printAllPlaylists(getPlaylist(id));
        } catch (ClassNotFoundException | SQLException | NoDataFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * It creates a playlist for a user
     *
     * @param userId The userId of the user who is creating the playlist.
     */
    public void addPlaylist(String userId) {
        SongMenu songMenu = new SongMenu();
        System.out.print("Enter the name of the playlist you want to create : ");
        scanner = new Scanner(System.in);
        String playlistName = scanner.nextLine();
        try {
            songMenu.listSongs();
            String songId = scanner.nextLine();
            PlaylistRepo playlistRepo = new PlaylistRepo();
            int result = playlistRepo.createPlaylist(userId, playlistName, songId);
            playlistLog.addPlaylistFeedback(result);
        } catch (SQLException | ClassNotFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * This function is used to add a song to a playlist
     *
     * @param userId The userId of the user who is logged in.
     */
    public void addSongToPlaylist(String userId) {
        SongMenu songMenu = new SongMenu();
        try {
            scanner = new Scanner(System.in);
            playlistRepo = new PlaylistRepo();
            List<Playlist> playlistLists = getPlaylist(userId);
            playlistLog.printAllPlaylists(playlistLists);
            System.out.print("\nEnter the Playlist id you want to add song to : ");
            int playListId = scanner.nextInt();
            songMenu.listSongs();
            scanner.nextLine();
            String songId = scanner.nextLine();
            int res = playlistRepo.insertIntoPlaylist(userId, "," + songId, playlistLists.get(playListId - 1).getId());
            playlistLog.addSongFeedback(res);
        } catch (SQLException | ClassNotFoundException | NoDataFoundException e) {
//            System.err.println(e.toString());
            e.printStackTrace();
        }
    }

    /**
     * This function is used to get all the songs in a particular playlist
     *
     * @param userId The userId of the user whose playlist is to be displayed.
     */
    public void getSongsInPlayList(String userId) {
        try {
            scanner = new Scanner(System.in);
            playlistRepo = new PlaylistRepo();
            List<Playlist> playlistLists = getPlaylist(userId);
            playlistLog.printAllPlaylists(playlistLists);
            System.out.print("\nEnter the Playlist id : ");
            int playListId = scanner.nextInt();
            List<String> playlistSongsList = playlistLists.get(playListId - 1).getSongId();
            List<Song> allSongs = new SongRepo().listAllSongs();
            List<Song> songsInPlayList = new ArrayList<>();
            for (Song song : allSongs) {
                if (playlistSongsList.contains(Integer.toString(song.getId()))) {
                    songsInPlayList.add(song);
                }
            }
            playlistLog.printAllSongsInPlaylist(songsInPlayList);
            System.out.println("\nPress 1 to play the playlist or 0 to Exit");
            if (scanner.nextInt() == 1) {
                new MusicPlayer().startPlaylist(songsInPlayList);
            }
        } catch (ClassNotFoundException | SQLException | NoDataFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * This function removes a playlist from the database
     *
     * @param userId The user's id
     */
    public void removePlaylist(String userId) {
        scanner = new Scanner(System.in);
        try {
            List<Playlist> playlistList = getPlaylist(userId);
            playlistLog.printAllPlaylists(playlistList);
            System.out.print("Enter the playlist id you wanna delete : ");
            int playlistId = scanner.nextInt();
            int deleteResult = playlistRepo.removePlaylist(userId, playlistList.get(playlistId - 1).getName());
            playlistLog.removePlaylistFeedback(deleteResult);
        } catch (SQLException | ClassNotFoundException | NoDataFoundException e) {
            System.err.println(e.getMessage());
        }
    }

    /**
     * It returns a list of all the playlists of a user
     *
     * @param userId The userId of the user whose playlist you want to see.
     * @return A list of all the playlists for a user.
     */
    private List<Playlist> getPlaylist(String userId) throws SQLException, ClassNotFoundException, NoDataFoundException {
        playlistRepo = new PlaylistRepo();
        return playlistRepo.showAllPlayLists(userId);
    }

}
