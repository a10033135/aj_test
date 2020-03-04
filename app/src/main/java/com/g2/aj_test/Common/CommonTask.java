package com.g2.aj_test.Common;

import android.nfc.Tag;
import android.os.AsyncTask;
import android.util.Log;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class CommonTask extends AsyncTask<String, Integer, String> {

    private final static String TAG = "TAG_CommonTask";
    private String url;

    public CommonTask(String url) {
        this.url = url;
    }

    @Override
    protected String doInBackground(String... strings) {
        HttpURLConnection connection;
        StringBuilder inStr = new StringBuilder();

        try {

            connection = (HttpURLConnection) new URL(url).openConnection();
            connection.setChunkedStreamingMode(0);
            connection.setUseCaches(false);
            connection.connect();
            Log.e(TAG, "responseCode: " + connection.getResponseCode());

            int responseCode = connection.getResponseCode();

            if (responseCode == 200) {

                BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));
                String line;
                while ((line = br.readLine()) != null) {

                    inStr.append(line);

                }

            }


        } catch (Exception e) {

            e.printStackTrace();
        }


        return inStr.toString();
    }
}
