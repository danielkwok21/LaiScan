package com.example.danielkwok.laiscan.Class;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class Marker extends RealmObject{

    @PrimaryKey
    private String id;

    private long createdAt;
    private String name;
    private String desc;
    private String latitude;
    private String longitude;

    public Marker(){
    }


    public Marker(String name, String desc, String latitude, String longitude){
        this.createdAt = System.currentTimeMillis();
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.desc = desc;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }
    public String getId() {
        return id;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public long getCreatedAt() {
        return createdAt;
    }
}
