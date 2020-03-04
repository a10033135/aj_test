package com.g2.aj_test;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;

import com.g2.aj_test.Common.Common;

public class MainActivity extends AppCompatActivity {

    private SharedPreferences pref;
    private final static String PREFERENCES_NAME = "preference";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getPref();
    }

    // 偵測是否曾使用過APP
    private void getPref(){
        pref = getSharedPreferences(PREFERENCES_NAME,MODE_PRIVATE);
        String str = "";
        if (pref.getString("used", "false").equals("true")){
            str = "歡迎回來";
        }else {
            str = "感謝您使用本APP";
            pref.edit().putString("used","true").apply();
        }
        Common.showCommonToast(this,str);
    }


}
