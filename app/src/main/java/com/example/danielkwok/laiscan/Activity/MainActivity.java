package com.example.danielkwok.laiscan.Activity;

import android.app.Dialog;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielkwok.laiscan.Class.Marker;
import com.example.danielkwok.laiscan.Class.Utils;
import com.example.danielkwok.laiscan.Database.RealmManager;
import com.example.danielkwok.laiscan.R;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.ErrorDialogFragment;
import com.google.android.gms.common.GoogleApiAvailability;
import com.google.android.gms.common.api.GoogleApi;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import io.realm.Realm;
import io.realm.RealmChangeListener;
import io.realm.RealmResults;


public class MainActivity extends AppCompatActivity{
    private static final String TAG = "MainActivity";
    private static final int ERROR_DIALOG_REQUEST = 9001;

    private Button main_scan_btn;
    private Button main_gen_btn;
    private TextView main_long_t;
    private TextView main_lat_t;
    private TextView main_name_t;
    private TextView main_desc_t;
    private IntentIntegrator QRScan;

    private Marker marker;
    private Marker latestMarker;
    private Realm realm;
    private RealmResults<Marker> results;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_scan_btn = (Button) findViewById(R.id.main_scan_btn);
        main_gen_btn = (Button) findViewById(R.id.main_gen_btn);
        main_long_t = (TextView) findViewById(R.id.main_long_t);
        main_lat_t =  (TextView) findViewById(R.id.main_lat_t);
        main_name_t = (TextView) findViewById(R.id.main_name_t);
        main_desc_t =  (TextView) findViewById(R.id.main_desc_t);
        QRScan = new IntentIntegrator(this);

        main_scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRScan.initiateScan();
            }
        });

        main_gen_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), QRGeneratorActivity.class);
                startActivity(intent);
            }
        });

        realm = RealmManager.open();
        results = realm.where(Marker.class).findAll();
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            if(result.getContents()==null){
                Toast.makeText(this,"Empty QR Code", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONObject obj = new JSONObject(result.getContents());
                    marker = new Marker(
                            obj.getString("name"),
                            obj.getString("desc"),
                            obj.getString("latitude"),
                            obj.getString("longitude"));
                    RealmManager.write(marker);
                    results.addChangeListener(new RealmChangeListener<RealmResults<Marker>>() {
                        @Override
                        public void onChange(RealmResults<Marker> element) {
                            realm = RealmManager.open();
                            latestMarker = RealmManager.getLatest();
                            populateText(latestMarker);
                        }
                    });

                }catch(JSONException e){
                    Log.d(TAG, e.toString());
                    Utils.writeToast(this, result.getContents());
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void populateText(Marker m){
        main_long_t.setText(m.getLongitude());
        main_lat_t.setText(m.getLatitude());
        main_name_t.setText(m.getName());
        main_desc_t.setText(m.getDesc());
    }

    @Override
    protected void onDestroy() {
        realm.close();
        super.onDestroy();
    }
}
