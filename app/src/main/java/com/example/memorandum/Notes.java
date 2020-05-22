package com.example.memorandum;

import android.graphics.Bitmap;

public class Notes {

    //private variables
    int id;
    String title;
    String content;
    String pinned;
    Bitmap image;

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
    public Notes(String title, String content, String pinned, Bitmap imageToStore){
        this.title = title;
        this.content = content;
        this.pinned = pinned;
        this.image = imageToStore;
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

    // getting image
    public Bitmap getImage(){
        return this.image;
    }

    // setting image
    public void setImage(Bitmap image){
        this.image = image;
    }


}
