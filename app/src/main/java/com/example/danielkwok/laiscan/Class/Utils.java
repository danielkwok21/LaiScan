package com.example.danielkwok.laiscan.Class;

import android.content.Context;
import android.widget.Toast;

public class Utils {
    public static void writeToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }
}
