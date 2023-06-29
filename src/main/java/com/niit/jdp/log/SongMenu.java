/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.log;

import com.niit.jdp.model.Song;
import com.niit.jdp.service.SongService;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class SongMenu {
    public int displayMenu(){
        System.out.println("\n=========================");
        System.out.println("Sr Number     Options");
        System.out.println("=========================");
        System.out.println("1              Pause");
        System.out.println("2              Resume");
        System.out.println("3              Next");
        System.out.println("4              Loop");
        System.out.println("5              Exit");
        System.out.println("=========================");
        System.out.print("Enter your choice : ");
        Scanner sc = new Scanner(System.in);
        return sc.nextInt();
    }
    public void displaySongDetail(Song song){
        System.out.println("\n"+song);
    }
    public void listSongs() throws SQLException, ClassNotFoundException {
        SongService songService = new SongService();
        List<Song> songList = songService.getAllSongs();
        System.out.println("\nSong List");
        System.out.println("=============");
        System.out.println(String.format("%-10s%-30s%-30s%-20s", "Id", "Name", "Artist", "Genre"));
        for (Song song : songList) {
            System.out.printf("%-9d%-30s%-30s%-20s%n", song.getId(), song.getName(), song.getArtistName(), song.getGenre());
        }
        System.out.print("\nEnter the ids of the song separated by commas you want to add in your playlist : ");
    }

    /**
     * This function takes a list of songs and prints them out in a table format
     *
     * @Param List
     */
    public void listSongs(List<Song> songList) {
        System.out.println("\nSong List");
        System.out.println("=============");
        System.out.println(String.format("%-10s%-30s%-30s%-20s", "Id", "Name", "Artist", "Genre"));
        for (Song song : songList) {
            System.out.printf("%-9d%-30s%-30s%-20s%n", song.getId(), song.getName(), song.getArtistName(), song.getGenre());
        }
    }
}
