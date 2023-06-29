package com.niit.jdp;

import com.niit.jdp.log.SongMenu;
import com.niit.jdp.log.UserLog;
import com.niit.jdp.service.MusicPlayer;
import com.niit.jdp.service.PlaylistService;
import com.niit.jdp.service.UserService;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        UserService user;
        UserLog userLog = new UserLog();
        MusicPlayer player = new MusicPlayer();
        SongMenu songMenu = new SongMenu();
        PlaylistService playlistService = new PlaylistService();
        user = new UserService();
        user.verifyUser();
        if (user.isVerified()) {
            userLog.printLoginMessage();
            int userChoice;
            Scanner sc = new Scanner(System.in);
            do {
                userLog.displayUserMenu();
                userChoice = sc.nextInt();
                switch (userChoice) {
                    case 1:
                        player.playSongWithId();
                        player.displaySongMenu(songMenu, player);
                        break;
                    case 2:
                        player.shuffle();
                        player.displaySongMenu(songMenu, player);
                        break;
                    case 3:
                        playlistService.getPlayLists(user.getId());
                        break;
                    case 4:
                        playlistService.getSongsInPlayList(user.getId());
                        break;
                    case 5:
                        playlistService.addPlaylist(user.getId());
                        break;
                    case 6:
                        playlistService.addSongToPlaylist(user.getId());
                        break;
                    case 7:
                        playlistService.removePlaylist(user.getId());
                        break;
                }
            } while (userChoice != 8);
        }
    }
}