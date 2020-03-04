package com.g2.aj_test.Common;

import android.app.Activity;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Formatter;

public class Common {

    public static void showCommonToast(Activity activity,String text){
        Toast.makeText(activity,text,Toast.LENGTH_LONG).show();
    }

    // 特殊時間專用Gson
    public static Gson getTimestampGson(){
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.setDateFormat("yyyy-MM-dd hh:mm:ss");
        Gson gson = gsonBuilder.create();

        return gson;
    }

    // 時間格式化
    public static String getDateFormat(Timestamp ts){
        Date date = new Date();
        date.setTime(ts.getTime());
        SimpleDateFormat sFormat = new SimpleDateFormat("yyyy年MM月dd日 hh時mm分");

        return sFormat.format(date);
    }


}
