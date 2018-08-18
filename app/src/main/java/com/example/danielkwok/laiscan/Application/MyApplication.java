package com.example.danielkwok.laiscan.Application;

import android.app.Application;

import io.realm.Realm;
import io.realm.RealmConfiguration;

public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();

        //realm initialize
        Realm.init(this);
        RealmConfiguration realmConfiguration =
                new RealmConfiguration.Builder()
                        .name("marker.db")
                        .deleteRealmIfMigrationNeeded()
                        .schemaVersion(1)
                        .build();

        Realm.setDefaultConfiguration(realmConfiguration);
    }

}