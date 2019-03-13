package com.example.aquafill;

public class InfoWindowData_Water {
    private static String upvotes1;
    private static String downvotes1;

    static String getUpvotes1(){
        return upvotes1;
    }

    void setUpvotes1(String upvotes1){
        InfoWindowData_Water.upvotes1 = upvotes1;
    }

    static String getDownvotes1(){
        return downvotes1;
    }

    void setDownvotes1(String downvotes1){
        InfoWindowData_Water.downvotes1 = downvotes1;
    }

}
