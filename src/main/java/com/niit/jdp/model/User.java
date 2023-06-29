/*
 * Author Name : Ashutosh Das
 * Date : 01-12-2022
 * Created with : IntelliJ Idea Community Edition
 */


package com.niit.jdp.model;

public class User {
    private String id;
    private String password;
    private String name;

    //Constructor
    public User(String id, String password, String name) {
        this.id = id;
        this.password = password;
        this.name = name;
    }
    public User() {
    }
    //Getter and Setter

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    //toString method

    @Override
    public String toString() {
        return  "User \n" +
                "============="+
                "\nid - " + id+
                "\npassword - " + password +
                "\nname - " + name +"\n";
    }
}
