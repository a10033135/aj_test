package com.g2.aj_test;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Map;

public class WeatherTime implements Serializable {
    Timestamp startTime ;
    Timestamp endTime ;
    Map<String,String> parameter;

    public Map<String, String> getParameter() {
        return parameter;
    }

    public String getParameterStr(){
        return parameter.get("parameterName")+parameter.get("parameterUnit");
    }

    public Timestamp getStartTime() {
        return startTime;
    }

    public void setStartTime(Timestamp startTime) {
        this.startTime = startTime;
    }

    public Timestamp getEndTime() {
        return endTime;
    }

    public void setEndTime(Timestamp endTime) {
        this.endTime = endTime;
    }

    public void setParameter(Map<String, String> parameter) {
        this.parameter = parameter;
    }
}
