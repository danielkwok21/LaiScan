package com.example.danielkwok.laiscan.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.danielkwok.laiscan.R;
import com.google.zxing.Result;

import me.dm7.barcodescanner.zxing.ZXingScannerView;

public class MainActivity extends AppCompatActivity{


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

}
