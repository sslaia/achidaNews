package com.blogspot.sslaia.achidanews.helpers;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AppUtils {



    public static String getDate(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("dd MMM yyyy");
            return sdf.format(date);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "DD";
        }
    }

    public static String getTime(String dateString) {

        try{
            SimpleDateFormat format1 = new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'");
            Date date = format1.parse(dateString);
            DateFormat sdf = new SimpleDateFormat("kk:mm");
            Date netDate = (date);
            return sdf.format(netDate);
        }
        catch(Exception ex) {
            ex.printStackTrace();
            return "HH";
        }
    }
}
