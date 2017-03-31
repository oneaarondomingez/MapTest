package com.example.myapplication.util;

import com.example.myapplication.model.beans.Pair;
import com.google.android.gms.maps.model.LatLng;

/**
 * Created by user on 3/15/17.
 */

public class LatLngFactory {
    public static LatLng create(Pair pair) {
        return new LatLng(pair.getLatitude(), pair.getLongitude());
    }

    public static Pair create(LatLng latLng) {
        return new Pair(latLng.latitude, latLng.longitude);
    }
}
