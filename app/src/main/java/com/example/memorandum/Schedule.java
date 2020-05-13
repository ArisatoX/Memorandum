package com.example.memorandum;

public class Schedule {

    //private variables
    int id;
    String title;
    String date;
    int done;

    // Empty constructor
    public Schedule(){

    }

    // constructor
    public Schedule(int id, String title, String date, int done){
        this.id = id;
        this.title = title;
        this.date = date;
        this.done = done;
    }

    // constructor
    public Schedule(String title, String date, int done){
        this.title = title;
        this.date = date;
        this.done = done;
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

    // getting date
    public String getDate(){
        return this.date;
    }

    // setting date
    public void setDate(String date){
        this.date = date;
    }

    // getting done
    public int getDone(){
        return this.done;
    }

    // setting done
    public void setDone(int done){
        this.done = done;
    }
}
