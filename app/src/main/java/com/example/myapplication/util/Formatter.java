package com.example.myapplication.util;

import android.widget.EditText;

import com.example.myapplication.model.beans.Pair;

/**
 * Created by user on 3/15/17.
 */

public class Formatter {
    public static Pair editTextToPair(EditText latitude, EditText longitude) {
        String latitudeString = latitude.getText().toString();
        String longitudeString = longitude.getText().toString();

        return new Pair(Double.valueOf(latitudeString), Double.valueOf(longitudeString));
    }
}
