package com.example.memorandum;

public class Notes {

    //private variables
    int id;
    String title;
    String content;
    String pinned;

    // Empty constructor
    public Notes(){

    }

    // constructor
    public Notes(int id, String title, String content){
        this.id = id;
        this.title = title;
        this.content = content;
    }

    // constructor
    public Notes(String title, String content){
        this.title = title;
        this.content = content;
    }

    // getting ID
    public int getID(){
        return this.id;
    }

    // setting id
    public void setID(int id){
        this.id = id;
    }

    // getting title
    public String getTitle(){
        return this.title;
    }

    // setting title
    public void setTitle(String title){
        this.title = title;
    }

    // getting content
    public String getContent(){
        return this.content;
    }

    // setting content
    public void setContent(String content){
        this.content = content;
    }

    // getting pinned
    public String getPinned(){
        return this.pinned;
    }

    // setting pinned
    public void setPinned(String pinned){
        this.pinned = pinned;
    }
}
