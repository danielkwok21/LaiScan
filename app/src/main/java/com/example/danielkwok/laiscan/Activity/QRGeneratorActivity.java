package com.example.danielkwok.laiscan.Activity;

import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.provider.MediaStore;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.util.Base64;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.danielkwok.laiscan.Class.QRCode;
import com.example.danielkwok.laiscan.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.journeyapps.barcodescanner.BarcodeEncoder;
import com.squareup.picasso.Picasso;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.InputStream;

public class QRGeneratorActivity extends AppCompatActivity {
    private static final String TAG = "QRGeneratorActivity";

    private ImageView register_qr_img;
    private EditText register_name_et;
    private EditText register_desc_et;
    private EditText register_lat_et;
    private EditText register_long_et;
    private Button register_new_loc_btn;

    private QRCode newQRCode;
    private Bitmap QRCodeBitmap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        register_qr_img = (ImageView) findViewById(R.id.register_qr_img);
        register_name_et = (EditText) findViewById(R.id.register_name_et);
        register_desc_et = (EditText) findViewById(R.id.register_desc_et);
        register_lat_et = (EditText) findViewById(R.id.register_lat_et);
        register_long_et = (EditText) findViewById(R.id.register_long_et);
        register_new_loc_btn = (Button) findViewById(R.id.register_new_loc_btn);

        register_new_loc_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getUserInput();
                register_qr_img.setImageBitmap(QRCodeBitmap);
            }
        });


    }

    private void getUserInput(){
        String name = register_name_et.getText().toString();
        String desc = register_desc_et.getText().toString();
        String latitude = register_lat_et.getText().toString();
        String longtitude = register_long_et.getText().toString();

        newQRCode = new QRCode(name, desc, latitude, longtitude);
        JSONObject jsonObject = generateJSON(newQRCode);
        generateQRCode(jsonObject);
    }

    private JSONObject generateJSON(QRCode q){
        JSONObject jsonObject= new JSONObject();
        try {
            jsonObject.put("name", q.getName());
            jsonObject.put("desc", q.getDesc());
            jsonObject.put("latitude", q.getLatitude());
            jsonObject.put("longtitude", q.getLongtitude());

        } catch (JSONException e) {
            Log.d(TAG, "generateJSON: "+e.toString());
        }
        return jsonObject;
    }

    private void generateQRCode(JSONObject j){
        String text=j.toString(); // Whatever you need to encode in the QR code
        MultiFormatWriter multiFormatWriter = new MultiFormatWriter();

        try {
            BitMatrix bitMatrix = multiFormatWriter.encode(text, BarcodeFormat.QR_CODE,200,200);
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            QRCodeBitmap = barcodeEncoder.createBitmap(bitMatrix);

        } catch (WriterException e) {
            Log.d(TAG, "generateJSON: "+e.toString());
        }
    }
}
