package com.example.reminderwithnotifi;

import android.app.Application;

public class Comman extends Application {

    public static String UserDate="";
    public static String UserTime="";

    public static void setUserDate(String userDate) {
        UserDate = userDate;
    }

    public static String getUserDate() {
        return UserDate;
    }

    public static void setUserTime(String userTime) {
        UserTime = userTime;
    }

    public static String getUserTime() {
        return UserTime;
    }
}
