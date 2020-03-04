package com.g2.aj_test;

import java.util.ArrayList;

public class WeatherElement {
    String elementName;

    public String getElementName() {
        return elementName;
    }

    public void setElementName(String elementName) {
        this.elementName = elementName;
    }


    ArrayList<WeatherTime> time;

    public ArrayList<WeatherTime> getTime() {
        return time;
    }

    public void setTime(ArrayList<WeatherTime> time) {
        this.time = time;
    }
}
