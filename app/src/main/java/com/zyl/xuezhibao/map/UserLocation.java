package com.zyl.xuezhibao.map;

/**
 * Created by Leo on 2017/4/17.
 */

public class UserLocation {

    public static String Latitude; //纬度
    public static String Longitude; //经度
    public static String address;

    public static String city;

    public static String getLongitude() {
//        return "44.4";
        return Longitude;
    }

    public static void setLongitude(String longitude) {
        Longitude = longitude;
    }

    public static String getLatitude() {
//        return "118.4";
        return Latitude;
    }

    public static void setLatitude(String latitude) {
        Latitude = latitude;
    }

    public static String getAddress() {
        return address;
    }

    public static void setAddress(String address) {
        UserLocation.address = address;
    }

    public static String getCity() {
        return city;
    }

    public static void setCity(String city) {
        UserLocation.city = city;
    }
}
