package com.example.sensorsapplication.OperationalClasses;

import java.util.Date;

public class SystemDateTime {

    public static String getDate() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat("yyyy:MM:dd");
            String currentDateandTime = sdf.format(new Date());
            return currentDateandTime;
        }
        return "";
    }

    public static String geTime() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat("HH:mm:ss.SSS");
            String currentDateandTime = sdf.format(new Date());
            return currentDateandTime;
        }
        return "";
    }

    public static String geTimeForCSV() {
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            android.icu.text.SimpleDateFormat sdf = new android.icu.text.SimpleDateFormat("HH:mm:ss");
            String currentDateandTime = sdf.format(new Date());
            return currentDateandTime;
        }
        return "";
    }

}
