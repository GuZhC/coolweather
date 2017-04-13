package com.example.coolweather.gson;

/**
 * Created by 83516 on 2017/4/12.
 */

public class AQI {
    public AQICity city;
    public class AQICity{
        public String aqi;
        public String pm25;
    }
}
