package com.example.user.adminproject.notice;

/**
 * Created by user on 2016-08-18.
 */
public class Notice_Value {

    String title;
    String contents;

    public Notice_Value(String title, String contents){

        this.title = title;
        this.contents = contents;
    }

    public String getTitle(){
        return title;
    }
    public String getContents(){
        return contents;
    }
    public void setTitle(String title){
        this.title = title;
    }
    public void setContents(String contents){
        this.contents = contents;
    }
}
