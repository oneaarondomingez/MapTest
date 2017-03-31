package com.example.myapplication.util;

import android.text.Editable;

import com.example.myapplication.model.beans.Pair;

/**
 * Created by user on 3/15/17.
 */

public class InputValidator {
    public static boolean validateInput(Pair from, Pair to) {
        if (from.getLatitude().isNaN() || from.getLongitude().isNaN()
                || to.getLatitude().isNaN() || to.getLongitude().isNaN()) {
            return false;
        }
        // TODO: 3/15/17 Add more validations (If they are actual numbers, etc.)
        return true;
    }

    public static boolean validateSpeed(Editable speed) {
        // TODO: 3/15/17 Implement this method, should be very similar to the method above
        return true;
    }
}
