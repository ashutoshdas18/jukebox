package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLException;
import java.util.List;

public class SongRepoTest {

    SongRepo songRepo;
    @Before
    public void setUp() throws Exception {
        songRepo = new SongRepo();
    }

    @After
    public void tearDown() throws Exception {
        songRepo = null;
    }

    @Test
    public void getSong() {
        try {
            Song actual = songRepo.getSong();
            Assert.assertNull("Value Mismatch", actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void getSongFail() {
        try {
            Song actual = songRepo.getSong();
            Song expected = null;
            Assert.assertNotEquals("Value Mismatch", expected, actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void listAllSongs() {
        try {
            List<Song> actual = songRepo.listAllSongs();
            Assert.assertNotNull("Obtained result is null", actual);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }

    @Test
    public void listAllSongsFailure() {
        try {
            List<Song> actual = songRepo.listAllSongs();
            List<Song> expected = null;
            Assert.assertNotEquals("Values mismatch", expected, actual);
        } catch (SQLException exception) {
            throw new RuntimeException(exception);
        }
    }
}