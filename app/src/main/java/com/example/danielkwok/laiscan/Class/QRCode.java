package com.example.danielkwok.laiscan.Class;

import java.util.UUID;

import io.realm.RealmObject;
import io.realm.annotations.PrimaryKey;

public class QRCode{

    private String name;
    private String desc;
    private String latitude;
    private String longtitude;


    public QRCode(String name, String desc, String latitude, String longtitude){
        this.name = name;
        this.desc = desc;
        this.latitude = latitude;
        this.longtitude = longtitude;
    }

    public String getName() {
        return name;
    }

    public String getDesc() {
        return desc;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongtitude() {
        return longtitude;
    }

}
