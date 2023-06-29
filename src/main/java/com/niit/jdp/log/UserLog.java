/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.log;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class UserLog {

    public int welcomeMessage() {
        Scanner sc = new Scanner(System.in);
        int x = 0;
        System.out.println("     ____.        __            ___.                    \n" +
                "    |    | __ __ |  | __  ____  \\_ |__    ____  ___  ___\n" +
                "    |    ||  |  \\|  |/ /_/ __ \\  | __ \\  /  _ \\ \\  \\/  /\n" +
                "/\\__|    ||  |  /|    < \\  ___/  | \\_\\ \\(  <_> ) >    < \n" +
                "\\________||____/ |__|_ \\ \\___  > |___  / \\____/ /__/\\_ \\\n" +
                "                      \\/     \\/      \\/               \\/");
        System.out.println("Sr Number     Options");
        System.out.println("=============================");
        System.out.println("1             Login");
        System.out.println("2             Register");
        System.out.println("=============================");
        System.out.print("Enter 1 to login 2 to register : ");
        x = sc.nextInt();
        return x;
    }

    public List<String> loginMessage() {
        System.out.println("\n-------------------------------");
        System.out.println("         Login");
        System.out.println("-------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your user id : ");
        String userName = sc.nextLine();
        System.out.print("Enter your password : ");
        String password = sc.nextLine();
        List<String> loginDetails = new ArrayList<>();
        loginDetails.add(0, userName);
        loginDetails.add(1, password);
        return loginDetails;
    }

    public List<String> registerMessage() {
        System.out.println("\n-------------------------------");
        System.out.println("         Registration");
        System.out.println("-------------------------------");
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter your user id : ");
        String userId = sc.nextLine();
        System.out.print("Enter your password : ");
        String userName = sc.nextLine();
        System.out.print("Enter your name : ");
        String password = sc.nextLine();
        List<String> loginDetails = new ArrayList<>();
        loginDetails.add(0, userId);
        loginDetails.add(1, userName);
        loginDetails.add(2, password);
        return loginDetails;
    }
    public void displayUserMenu(){
        System.out.println("\n=============================");
        System.out.println("Sr Number     Options");
        System.out.println("=============================");
        System.out.println("1           Play Song");
        System.out.println("2           Shuffle Play");
        System.out.println("3           Show Playlists");
        System.out.println("4           Show Songs In Playlist");
        System.out.println("5           Add a Playlist");
        System.out.println("6           Add to Playlist");
        System.out.println("7           Delete Playlist");
        System.out.println("8           Exit");
        System.out.println("=============================");
        System.out.print("Enter Choice : ");
    }
    public void printLoginMessage(){
        System.out.println("\n\u001B[33mLogIn Successful !\u001B[0m");
    }
}
