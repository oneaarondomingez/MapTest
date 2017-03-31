package com.example.myapplication.map;

import com.example.myapplication.model.beans.Pair;
import com.google.android.gms.maps.model.LatLng;

import java.util.List;

/**
 * Created by user on 3/15/17.
 */

public interface MapView {
    void showErrorValidation(String error);

    void paintMarkers(Pair from, Pair to);

    void moveCamera(Pair destination, float zoomRate);

    void drawPolyline(List<LatLng> latLngs);

    void moveRoute(LatLng LatLng, float zoomRate);
}
