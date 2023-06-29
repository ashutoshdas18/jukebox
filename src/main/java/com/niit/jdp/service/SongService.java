/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.service;

import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepo;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SongService {

    /**
     * "Get a song from the database."
     * @return A song object
     */
    public Song getSong() {
        try {
            SongRepo songRepo = new SongRepo();
            return songRepo.getSong();
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * I'm going to try to get all the songs from the database, but if I can't, I'm going to return an empty list.
     *
     * @return A list of all songs in the database.
     */
    public List<Song> getAllSongs() {
        try {
            SongRepo songRepo = new SongRepo();
            return songRepo.listAllSongs();
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
            return new ArrayList<>();
        }
    }

}
