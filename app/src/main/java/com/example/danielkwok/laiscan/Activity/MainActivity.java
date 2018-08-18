package com.example.danielkwok.laiscan.Activity;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.danielkwok.laiscan.R;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;


public class MainActivity extends AppCompatActivity{
    private Button main_scan_btn;
    private TextView main_long_t, main_lat_t;
    private IntentIntegrator QRScan;
    private static final String TAG = "MainActivity";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        main_scan_btn = findViewById(R.id.main_scan_btn);
        main_long_t = findViewById(R.id.main_long_t);
        main_lat_t = findViewById(R.id.main_lat_t);
        QRScan = new IntentIntegrator(this);

        main_scan_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QRScan.initiateScan();
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
        if(result!=null){
            //if qrcode is empty
            if(result.getContents()==null){
                Toast.makeText(this,"Empty QR Code", Toast.LENGTH_LONG).show();
            }else{
                try{
                    JSONObject obj = new JSONObject(result.getContents());
                    main_long_t.setText(obj.getString("long"));
                    main_lat_t.setText(obj.getString("lat"));
                }catch(JSONException e){
                    Log.d(TAG, e.toString());
                    Toast.makeText(this, result.getContents(), Toast.LENGTH_LONG).show();
                }
            }
        }else{
            super.onActivityResult(requestCode, resultCode, data);
        }
    }
}
