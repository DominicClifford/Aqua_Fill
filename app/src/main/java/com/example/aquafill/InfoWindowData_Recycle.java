package com.example.aquafill;

public class InfoWindowData_Recycle {

    private static String upvotes;
    private static String downvotes;

    static String getUpvotes(){
        return upvotes;
    }

    void setUpvotes(String upvotes){
        InfoWindowData_Recycle.upvotes = upvotes;
    }

    static String getDownvotes(){
        return downvotes;
    }

    void setDownvotes(String downvotes){
        InfoWindowData_Recycle.downvotes = downvotes;
    }
}
