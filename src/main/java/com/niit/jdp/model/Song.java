/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.model;

import java.time.LocalDate;
import java.util.Objects;

public class Song {
    private int id;
    private String name;
    private String artistName;
    private String genre;
    private LocalDate releaseDate;

    //Constructors

    public Song(int id, String name, String artistName, String genre, LocalDate releaseDate) {
        this.id = id;
        this.name = name;
        this.artistName = artistName;
        this.genre = genre;
        this.releaseDate = releaseDate;
    }
    public  Song(){

    }
    //Getters and Setters

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getArtistName() {
        return artistName;
    }

    public void setArtistName(String artistName) {
        this.artistName = artistName;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public LocalDate getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(LocalDate releaseDate) {
        this.releaseDate = releaseDate;
    }

    //toString
    @Override
    public String toString() {
        return "Song \n" +
                "============="+
                "\nName - " + name+
                "\nArtist name - " + artistName +
                "\nGenre - " + genre+
                "\nRelease date - " + releaseDate+"\n";
    }

    //Hash code
    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getArtistName(), getGenre(), getReleaseDate());
    }
}
