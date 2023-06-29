/*
 * Author Name : Ashutosh Das
 * Date : 30-11-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.service;

import com.niit.jdp.log.SongMenu;
import com.niit.jdp.model.Song;
import com.niit.jdp.repository.SongRepo;

import javax.sound.sampled.*;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class MusicPlayer implements Runnable {

    private Clip clip;
    private String songName;
    private Thread playerThread;
    private Song[] songArr;
    private int currentIndex = 0;

    public Clip getClip() {
        return clip;
    }

    public void setSongName(String songName) {
        this.songName = songName;
    }

    @Override
    public void run() {

        playMusic(songName);
    }


    /**
     * It takes a song name as a parameter, finds the song in the resources folder, and plays it
     *
     * @param songName The name of the song you want to play.
     */
    public void playMusic(String songName) {
        File file = new File("src/main/resources/songs/" + songName);

        //Create an audioInputStream
        try {
            AudioInputStream audioInputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            System.out.println(e.getMessage());
        }

    }

    /**
     * The function is called when the user clicks on the shuffle button. It stops the current song, gets a new song from
     * the database, displays the song details and starts playing the new song
     *
     */
    public void shuffle() {
        currentIndex = 0;
        SongService songService = new SongService();
        List<Song> songList = songService.getAllSongs();
        songArr = new Song[songList.size()];
        for (Song song : songList) {
            int rand = new Random().nextInt(songList.size());
            while (songArr[rand] != null) {
                rand = new Random().nextInt(songList.size());
            }
            songArr[rand] = song;
        }
        playSong(songArr[currentIndex]);
    }

    /**
     * It takes the id of the song from the user and plays the song
     */
    public void playSongWithId() {
        try {
            SongMenu songMenu = new SongMenu();
            SongRepo songRepo = new SongRepo();
            List<Song> songList = songRepo.listAllSongs();
            songMenu.listSongs(songList);
            System.out.print("Enter the id of the song you wanna play : ");
            Scanner scanner = new Scanner(System.in);
            currentIndex = scanner.nextInt() - 1;
            songArr = new Song[songList.size()];
            songList.toArray(songArr);
            playSong(songList.get(currentIndex));
        } catch (SQLException | ClassNotFoundException exception) {
            System.out.println(exception.getMessage());
        }
    }

    /**
     * This function takes a list of songs and converts it to an array of songs, then plays the first song in the array and
     * displays the song menu
     *
     * @param songList The list of songs to be played.
     */
    public void startPlaylist(List<Song> songList) {
        songArr = new Song[songList.size()];
        currentIndex = 0;
        songList.toArray(songArr);
        System.out.println(Arrays.toString(songArr));
        playSong(songArr[currentIndex]);
        displaySongMenu(new SongMenu(), this);
    }

    /**
     * This method stops the music
     */
    public void stopMusic() {
        if (clip != null) {
            clip.close();
        }
    }

    /**
     * If the clip is not null, start it.
     */
    public void resume() {
        if (clip != null) {
            clip.start();
        }
    }

    /**
     * If the clip is not null, stops it.
     */
    public void pause() {
        if (clip != null) {
            clip.stop();
        }
    }

    /**
     * If the clip is not null, loop it continuously.
     */
    public void loop() {
        if (clip != null) {
            clip.loop(Clip.LOOP_CONTINUOUSLY);
        }
    }

    /**
     * This function stops the music and interrupts the player thread
     */
    public void reset() {
        this.stopMusic();
        if (this.playerThread != null) {
            this.playerThread.interrupt();
        }

    }

    /**
     * The function displays a menu of options for the user to choose from, and then performs the appropriate action based
     * on the user's choice
     *
     * @param songMenu The menu that will be displayed to the user.
     * @param player   The MusicPlayer object that is currently playing the song.
     */
    public void displaySongMenu(SongMenu songMenu, MusicPlayer player) {
        int playerChoice = songMenu.displayMenu();
        while (playerChoice != 5) {
            switch (playerChoice) {
                case 1:
                    player.pause();
                    if (!player.getClip().isActive()) {
                        System.out.println("\nPaused");
                    }
                    break;
                case 2:
                    player.resume();
                    if (player.getClip().isActive()) {
                        System.out.println("\nResumed");
                    }
                    break;
                case 3:
                    player.next();
                    break;
                case 4:
                    player.loop();
                    break;
            }
            playerChoice = songMenu.displayMenu();
        }
        System.out.println("\nExiting player menu ...");
        player.reset();
    }

    /**
     * If the current index is the last index in the array, set the current index to 0, otherwise increment the current
     * index and play the song at that index
     */
    public void next() {
        if (currentIndex == songArr.length - 1) {
            currentIndex = 0;
        } else {
            currentIndex++;
        }
        playSong(songArr[currentIndex]);
    }

    /**
     * This function is called when a song is selected from the song list. It stops the current song, sets the song name to
     * the selected song, and starts the new song
     *
     * @param song The song object that is being played
     */
    public void playSong(Song song) {
        if (this.playerThread != null) {
            playerThread.interrupt();
        }
        this.stopMusic();
        String songName = song.getName();
        this.setSongName(songName + ".wav");
        playerThread = new Thread(this);
        playerThread.start();
        SongMenu menu = new SongMenu();
        menu.displaySongDetail(song);
    }
}


