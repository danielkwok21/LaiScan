package com.example.danielkwok.laiscan.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.danielkwok.laiscan.Class.Marker;
import com.example.danielkwok.laiscan.Database.RealmManager;
import com.example.danielkwok.laiscan.R;
import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

import java.math.BigDecimal;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmObject;
import io.realm.RealmResults;


public class MapsFragment extends Fragment implements OnMapReadyCallback{
    private static final String TAG = "MapsFragment";

    private SupportMapFragment supportMapFragment;
    private GoogleMap mMap;

    private Realm realm;
    private RealmResults<Marker> results;

    private double latitude = 38.897770;
    private double longitude =  -77.036527;

    public MapsFragment(){
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v = inflater.inflate(R.layout.fragment_maps, container, false);
        supportMapFragment = (SupportMapFragment)getChildFragmentManager().findFragmentById(R.id.map);
        if(supportMapFragment==null){
            FragmentManager fragmentManager = getFragmentManager();
            FragmentTransaction fragmentTransaction= fragmentManager.beginTransaction();
            supportMapFragment = SupportMapFragment.newInstance();
            fragmentTransaction.replace(R.id.map, supportMapFragment).commit();
        }
        supportMapFragment.getMapAsync(this);

        realm = RealmManager.open();
        results = realm.where(Marker.class).findAll();

        return v;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;
        results.addChangeListener(new RealmChangeListener<RealmResults<Marker>>() {
            @Override
            public void onChange(RealmResults<Marker> element) {
                realm = RealmManager.open();
                newCoordinates();
            }
        });
        newCoordinates();
    }

    private void newCoordinates(){
        Marker newMarker;
        try{
            newMarker = RealmManager.getLatest();
            latitude = Double.parseDouble(newMarker.getLatitude());
            longitude = Double.parseDouble(newMarker.getLongitude());

            // Add a new marker and move the camera
            LatLng newCoordinate = new LatLng(latitude, longitude);
            mMap.addMarker(new MarkerOptions().position(newCoordinate).title(newMarker.getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLng(newCoordinate));
//            CameraPosition cameraPosition = new CameraPosition.Builder()
//                    .target(newCoordinate)
//                    .zoom(17).build();
//            //Zoom in and animate the camera.
//            mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));

        }catch(Exception e){
            Log.d(TAG, e.toString());
        }
    }


}
