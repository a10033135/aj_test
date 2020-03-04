package com.g2.aj_test.Fragment;


import android.app.Activity;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.g2.aj_test.Common.Common;
import com.g2.aj_test.R;
import com.g2.aj_test.WeatherTime;


/**
 * A simple {@link Fragment} subclass.
 */
public class DetailFragment extends Fragment {

    View view;
    Activity activity;
    WeatherTime time;

    TextView tvTimeStart, tvTimeEnd, tvTemp;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_detail, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        this.view = view;
        activity = getActivity();
        holdView();
    }

    private void holdView() {

        activity.setTitle("第二頁");

        Bundle bundle = getArguments();
        time = (WeatherTime) bundle.getSerializable("timeData");

        tvTimeStart = activity.findViewById(R.id.detail_tvTimeStart);
        tvTimeEnd = activity.findViewById(R.id.detail_tvTimeEnd);
        tvTemp = activity.findViewById(R.id.detail_tvTemp);

        tvTimeStart.setText(Common.getDateFormat(time.getStartTime()));
        tvTimeEnd.setText(Common.getDateFormat(time.getEndTime()));
        tvTemp.setText(time.getParameterStr());

    }

}
