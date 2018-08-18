package com.example.danielkwok.laiscan.Activity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danielkwok.laiscan.R;

public class RegisterActivity extends AppCompatActivity {

    private ImageView register_loc_img;
    private EditText register_name_et;
    private EditText register_desc_et;
    private EditText register_lat_et;
    private EditText register_long_et;
    private Button register_new_loc_btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_loc_img = (ImageView) findViewById(R.id.register_loc_img);
        register_name_et = (EditText) findViewById(R.id.register_name_et);
        register_desc_et = (EditText) findViewById(R.id.register_desc_et);
        register_lat_et = (EditText) findViewById(R.id.register_lat_et);
        register_long_et = (EditText) findViewById(R.id.register_long_et);
        register_new_loc_btn = (Button) findViewById(R.id.register_new_loc_btn);
    }
}
