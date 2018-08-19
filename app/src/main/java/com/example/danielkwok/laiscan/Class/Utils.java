package com.example.danielkwok.laiscan.Class;

import android.content.Context;
import android.widget.EditText;
import android.widget.Toast;

public class Utils {
    public static void writeToast(Context context,String text){
        Toast.makeText(context, text, Toast.LENGTH_LONG).show();
    }

    public static void validate(EditText editText) throws Exception {
        String input = editText.getText().toString().trim();
        if(input.length() == 0){
            throw new Exception();
        }
    }

    public static void emptyFieldWarning(Context context){
        Toast.makeText(context, "Empty Field Detected", Toast.LENGTH_LONG).show();
    }

}
