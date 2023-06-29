package com.niit.jdp.repository;

import com.niit.jdp.exception.NoDataFoundException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseConnection;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class PlaylistRepoTest {

    PlaylistRepo playlistRepo;
    String userId = "ashu145";

    @Before
    public void setUp() throws Exception {
        playlistRepo = new PlaylistRepo();
    }

    @After
    public void tearDown() throws Exception {
        playlistRepo = null;
    }

    @Test
    public void showAllPlayLists() {
        try {
            List<Playlist> playlistList = playlistRepo.showAllPlayLists(userId);
            int expected = playlistList.size();
            Connection connection = new DatabaseConnection().getConnection();
            String query = "select  distinct `name` from `playlists` where `userId`=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<String> actualResultList = new ArrayList<>();
            while (resultSet.next()) {
                actualResultList.add(resultSet.getString("name"));
            }
            assertEquals("Excepted value doesn't match with actual value", expected, actualResultList.size());
        } catch (SQLException | ClassNotFoundException | NoDataFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = NoDataFoundException.class)
    public void showAllPlayListsFail() throws SQLException, NoDataFoundException {
        List<Playlist> playlistList = playlistRepo.showAllPlayLists("someIdNotRegistered");
    }

    @Test
    public void createPlaylist() {
        try {
            String playlistName = "randomPlayList";
            int expected = 1;
            int actual = playlistRepo.createPlaylist(userId, playlistName, "5");
            assertEquals("Mismatch in expected and actual values", expected, actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = SQLException.class)
    public void createPlaylistFail() throws SQLException {
        String playlistName = "randomPlayList";
        playlistRepo.createPlaylist(null, playlistName, "5");
    }

    @Test
    public void showAllSongsInPlaylist() {
        try {
            int actual = playlistRepo.showAllSongsInPlaylist(userId, "test").length;
            Connection connection = new DatabaseConnection().getConnection();
            String query = "select `playlists`.`songId` from `playlists` where `playlists`.`id`='2' and `playlists`.`userId`=?";
            PreparedStatement statement = connection.prepareStatement(query);
            statement.setString(1, userId);
            ResultSet resultSet = statement.executeQuery();
            List<String> expectResult = new ArrayList<>();
            resultSet.next();
            String songIds = resultSet.getString("songId");
            assertEquals("Expected and Actual mismatch", songIds.split(",").length, actual);
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Test(expected = SQLException.class)
    public void showAllSongsInPlaylistFail() throws SQLException {
        String[] songList = playlistRepo.showAllSongsInPlaylist(userId, "randomPlaylistList");
    }

    @Test
    public void removePlaylist() {
        int expected = 1;
        String playlistName = "randomPlayList";
        try {
            int actual = playlistRepo.removePlaylist(userId, playlistName);
            assertEquals("Values mismatch", expected, actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Test
    public void removePlaylistFail() {
        int expected = 1;
        String playlistName = "NotAPlaylist";
        try {
            int actual = playlistRepo.removePlaylist(userId, playlistName);
            assertNotEquals("Values mismatch", expected, actual);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}