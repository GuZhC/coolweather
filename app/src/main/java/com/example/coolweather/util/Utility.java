package com.example.coolweather.util;

import android.text.TextUtils;

import com.example.coolweather.db.City;
import com.example.coolweather.db.County;
import com.example.coolweather.db.Province;
import com.example.coolweather.gson.Weather;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by 83516 on 2017/4/11.
 */

public class Utility {

    public static Weather handleWeatherResponse(String response){
        try {
            JSONObject jsonObject = new JSONObject(response);
            JSONArray jsonArray = jsonObject.getJSONArray("HeWeather");
            String weatherContent = jsonArray.getJSONObject(0).toString();
            return new Gson().fromJson(weatherContent,Weather.class);

        } catch (JSONException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean handleProvinceResponse(String respponse){
        if (!TextUtils.isEmpty(respponse)){
            try {
                JSONArray allProvinces = new JSONArray(respponse);
                for (int i = 0; i<allProvinces.length();i++){
                    JSONObject provinceObject = allProvinces.getJSONObject(i);
                    Province province = new Province();
                    province.setProvinceName(provinceObject.getString("name"));
                    province.setProvinceCode(provinceObject.getInt("id"));
                    province.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }
      public static boolean handleCityResponse(String respponse,int provinceId){
        if (!TextUtils.isEmpty(respponse)){
            try {
                JSONArray allCities = new JSONArray(respponse);
                for (int i = 0; i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    City city = new City();
                    city.setCityName(cityObject.getString("name"));
                    city.setCityCode(cityObject.getInt("id"));
                    city.setProvinceId(provinceId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }
    public static boolean handleCountyResponse(String respponse,int cityId){
        if (!TextUtils.isEmpty(respponse)){
            try {
                JSONArray allCities = new JSONArray(respponse);
                for (int i = 0; i<allCities.length();i++){
                    JSONObject cityObject = allCities.getJSONObject(i);
                    County city = new County();
                    city.setCountyName(cityObject.getString("name"));
                    city.setWeatherId(cityObject.getString("weather_id"));
                    city.setCityId(cityId);
                    city.save();
                }
                return true;
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return  false;
    }


}
