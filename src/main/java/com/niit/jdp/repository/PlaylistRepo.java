/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.repository;

import com.niit.jdp.exception.NoDataFoundException;
import com.niit.jdp.model.Playlist;
import com.niit.jdp.service.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlaylistRepo {
    Connection connection;

    //Constructor
    public PlaylistRepo() throws ClassNotFoundException, SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    /**
     * This function returns a list of all the playlists of a user
     *
     * @param id the id of the user
     * @return A list of all the playlists that the user has created.
     */
    public List<Playlist> showAllPlayLists(String id) throws SQLException, NoDataFoundException {
        List<Playlist> playlistList = new ArrayList<>();
        String query = "select * from `playlists` where `userId`=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, id);
        ResultSet resultSet = statement.executeQuery();
        while (resultSet.next()) {
            List<String> songsList = new ArrayList<>(Arrays.asList(resultSet.getString("songId").split(",")));
            Playlist playlist = new Playlist(resultSet.getInt("id"), resultSet.getString("name"), songsList, resultSet.getString("userId"));
            playlistList.add(playlist);
        }
        if (playlistList.size() > 0) {
            return playlistList;
        } else {
            throw new NoDataFoundException("No available playlists were found");
        }
    }

    /**
     * It creates a playlist with the given name, songId and userId
     *
     * @param userId       The userId of the user who is creating the playlist.
     * @param playlistName The name of the playlist
     * @param songId       The id of the song that you want to add to the playlist.
     * @return int - number of rows affected
     */
    public int createPlaylist(String userId, String playlistName, String songId) throws SQLException {
        String query = "insert  into  `playlists` (name,songId,userId) values(?,?,?)";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playlistName);
        statement.setString(2, songId.trim());
        statement.setString(3, userId);
        return statement.executeUpdate();
    }

    /**
     * It takes a userId, songId, and playlistId and inserts the songId into the playlistId for the userId
     *
     * @param userId     The userId of the user who created the playlist
     * @param songId     The songId of the song to be added to the playlist.
     * @param playlistId The id of the playlist to which the song is to be added.
     * @return The number of rows affected by the query.
     */
    public int insertIntoPlaylist(String userId, String songId, int playlistId) throws SQLException {
        String query = "update `playlists` set `songId` = concat(`songId`,?) where userId=? and id=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, songId.trim());
        statement.setString(2, userId);
        statement.setInt(3, playlistId);
        return statement.executeUpdate();
    }

    /**
     * This function returns a list of songs in a playlist
     *
     * @param userId       The userId of the user who created the playlist.
     * @param playlistName The name of the playlist you want to see the songs of.
     * @return A list of songs that are in the playlist.
     */
    public String[] showAllSongsInPlaylist(String userId, String playlistName) throws SQLException {
        String query = "select `songId` from `playlists` where `playlists`.`name`=? and `userId`= ?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playlistName);
        statement.setString(2, userId);
        ResultSet resultSet = statement.executeQuery();
        resultSet.next();
        return resultSet.getString("songId").split(",");
    }

    /**
     * It deletes a playlist from the database
     *
     * @param userId       The userId of the user who owns the playlist
     * @param playlistName The name of the playlist to be deleted.
     * @return The number of rows affected by the query.
     */
    public int removePlaylist(String userId, String playlistName) throws SQLException {
        String query = "delete from `playlists` where `name` = ? and `userId`=?";
        PreparedStatement statement = connection.prepareStatement(query);
        statement.setString(1, playlistName);
        statement.setString(2, userId);
        return statement.executeUpdate();
    }
}
