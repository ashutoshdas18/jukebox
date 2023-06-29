/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.repository;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SongRepo {
    Connection connection;

    //Constructor
    public SongRepo() throws ClassNotFoundException,SQLException {
        DatabaseConnection databaseConnection = new DatabaseConnection();
        connection = databaseConnection.getConnection();
    }

    //Get song
    /**
     * > This function returns a random single song
     *
     * @return String
     */
    public Song getSong() throws SQLException{
        String query = "select * from songs";
        Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery(query);
        List<Song> songsList =new ArrayList<>();
        while (resultSet.next()){
            Song song = new Song(resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("artist_name"),resultSet.getString("genre"),resultSet.getDate("releaseDate").toLocalDate());
            songsList.add(song);
        }
        return  songsList.get(new Random().nextInt(songsList.size()-1));
    }

    /**
     * This function returns a list of all songs in the database.
     * @return List &lt;Songs&gt; A list of songs
     */
    public List<Song> listAllSongs() throws SQLException {
        String query = "select * from `songs`";
        PreparedStatement statement = connection.prepareStatement(query);
        ResultSet resultSet = statement.executeQuery();
        List<Song> songList = new ArrayList<>();
        while (resultSet.next()) {
            Song song = new Song(resultSet.getInt("id"), resultSet.getString("name"), resultSet.getString("artist_name"), resultSet.getString("genre"), resultSet.getDate("releaseDate").toLocalDate());
            songList.add(song);
        }
        return songList;
    }
}
