package com.g2.aj_test;

import java.util.ArrayList;

public class WeatherLocation {
    String locationName ;
    ArrayList<WeatherElement> weatherElement;

    public String getLocationName() {
        return locationName;
    }

    public void setLocationName(String locationName) {
        this.locationName = locationName;
    }

    public ArrayList<WeatherElement> getWeatherElement() {
        return weatherElement;
    }

    public void setWeatherElement(ArrayList<WeatherElement> weatherElement) {
        this.weatherElement = weatherElement;
    }

    public ArrayList<WeatherTime> getTimeList(){
        return weatherElement.get(0).getTime();
    }

}
