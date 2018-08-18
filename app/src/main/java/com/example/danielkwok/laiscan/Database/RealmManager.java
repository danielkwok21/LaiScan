package com.example.danielkwok.laiscan.Database;

import android.content.Context;
import android.util.Log;

import com.example.danielkwok.laiscan.Class.Marker;

import io.realm.Realm;
import io.realm.RealmObject;
import io.realm.RealmResults;

public class RealmManager {

    private static final String TAG = "RealmManager";
    private static Realm realm;

    public static Realm open(){
        realm = Realm.getDefaultInstance();
        return realm;
    }

    public static void close(){
        if(realm!=null){
            realm.close();
        }
    }

    //always call open() before calling write() and close() after
    public static void write(RealmObject n){
        realm.beginTransaction();
        realm.insertOrUpdate(n);
        realm.commitTransaction();
    }


    //reads from database, based on className type
    //(if within package, has to be included as well
    //has potential to expand to include more parameters (maybe?)
    public static <E extends RealmObject> RealmResults<E> read(Context context, String className){
        String packageName = context.getPackageName();
        RealmResults<E> realmObj = null;

        try {
            Class objectClass = Class.forName(packageName+"."+className);

            //realm read
            realm.beginTransaction();
            realmObj = realm.where(objectClass).findAll();
            realm.commitTransaction();

        } catch (ClassNotFoundException e) {
            Log.d(TAG, e.toString());
        }

        return realmObj;
    }

    //empty out db
    //always call open() before calling clear() and close() after
    public static void clear(RealmResults results) {
        results.deleteAllFromRealm();
    }

}
