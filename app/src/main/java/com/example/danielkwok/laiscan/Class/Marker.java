package com.example.danielkwok.laiscan.Class;

import io.realm.RealmObject;

public class Marker extends RealmObject{
    private String latitude;
    private String longtitude;

    public Marker(){
    }

    public Marker(String latitude, String longtitude){
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }
}
