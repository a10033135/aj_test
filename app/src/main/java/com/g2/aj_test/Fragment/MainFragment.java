package com.g2.aj_test.Fragment;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.g2.aj_test.Common.Common;
import com.g2.aj_test.Common.CommonTask;
import com.g2.aj_test.WeatherLocation;
import com.g2.aj_test.R;
import com.g2.aj_test.WeatherTime;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 */
public class MainFragment extends Fragment implements View.OnClickListener {

    private View view;
    private RecyclerView rv;

    private Activity activity;
    private ArrayList<WeatherTime> weatherTimeList;

    private final String url = "https://opendata.cwb.gov.tw/api/v1/rest/datastore/F-C0032-001?Authorization=CWB-7517E4C9-8AFC-4173-8B33-3AE0E73486AD&limit=20&offset=0&format=JSON&locationName=%E8%87%BA%E5%8C%97%E5%B8%82&elementName=MinT&sort=time";
    private final String TAG = "MainFragment";


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        activity = getActivity();
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        weatherTimeList = getData();
        holdView();
    }


    // 綁定View
    private void holdView() {
        activity.setTitle("第一頁");

        rv = view.findViewById(R.id.main_rv);
        rv.setLayoutManager(new LinearLayoutManager(activity.getApplicationContext()));
        rv.setAdapter(new MainRecyclerViewAdapter(weatherTimeList, activity));
    }


    // 抓取 API 資料
    private ArrayList<WeatherTime> getData() {

        ArrayList<WeatherTime> weatherTimes = null;

        try {
            CommonTask commonTask = new CommonTask(url);
            String resultStr = commonTask.execute().get();
            // 抓取的文字串
            Log.e(TAG, resultStr);

            // 轉換成jsonObject，直接抓取需要的資料
            JsonObject jsonObject = new Gson().fromJson(resultStr, JsonObject.class);
            JsonArray locationList = ((JsonObject) jsonObject.get("records")).get("location").getAsJsonArray();
            WeatherLocation location = Common.getTimestampGson().fromJson(locationList.get(0).toString(), WeatherLocation.class);
            Log.e(TAG, location.getLocationName());
            
            weatherTimes = location.getWeatherElement().get(0).getTime();
            Log.e(TAG,"資料成功擷取");
            // 要用的資料

        } catch (Exception e) {
            e.printStackTrace();
        }

        return weatherTimes;
    }

    // TypeA 點擊切換頁面
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.itemView_main_csTypeA) {
            Bundle bundle = new Bundle();
            bundle.putSerializable("timeData", weatherTimeList.get((Integer) v.getTag()));
            Navigation.findNavController(view).navigate(R.id.action_mainFragment_to_detailFragment, bundle);
        }
    }


    private class MainRecyclerViewAdapter extends RecyclerView.Adapter<MainRecyclerViewAdapter.MainViewHolder> {
        ArrayList<WeatherTime> timeList;
        Context activity;

        public MainRecyclerViewAdapter(ArrayList<WeatherTime> timeList, Context activity) {
            this.timeList = timeList;
            this.activity = activity;
        }

        @Override
        public int getItemCount() {
            return timeList.size();
        }

        @NonNull
        @Override
        public MainRecyclerViewAdapter.MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View itemView = LayoutInflater.from(activity).inflate(R.layout.itemview_main, parent, false);

            return new MainViewHolder(itemView);
        }

        private class MainViewHolder extends RecyclerView.ViewHolder {
            ConstraintLayout csTypeA, csTypeB;
            TextView tvTimeStart, tvTimeEnd, tvTemp;
            ImageView ivImage;

            private MainViewHolder(View itemView) {
                super(itemView);
                csTypeA = itemView.findViewById(R.id.itemView_main_csTypeA);
                csTypeB = itemView.findViewById(R.id.itemView_main_csTypeB);
                tvTimeStart = itemView.findViewById(R.id.itemView_main_tvTimeStart);
                tvTimeEnd = itemView.findViewById(R.id.itemView_main_tvTimeEnd);
                tvTemp = itemView.findViewById(R.id.itemView_main_tvTemp);
                ivImage = itemView.findViewById(R.id.itemView_main_ivImage);
            }
        }

        @SuppressLint("SetTextI18n")
        @Override
        public void onBindViewHolder(@NonNull MainRecyclerViewAdapter.MainViewHolder holder, int position) {

            WeatherTime time = timeList.get(position);
            holder.tvTemp.setText(time.getParameterStr());
            holder.tvTimeStart.setText(Common.getDateFormat(time.getStartTime()));
            holder.tvTimeEnd.setText(Common.getDateFormat(time.getEndTime()));
            holder.csTypeA.setTag(position);
            holder.csTypeA.setOnClickListener(MainFragment.this);

        }
    }
}
