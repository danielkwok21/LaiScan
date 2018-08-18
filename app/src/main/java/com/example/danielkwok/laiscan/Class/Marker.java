package com.example.danielkwok.laiscan.Class;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Marker extends RealmObject{

    @PrimaryKey
    private String id;


    private long createdAt;
    private String latitude;
    private String longtitude;

    public Marker(){
    }

    public Marker(String latitude, String longtitude){
        this.createdAt = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
