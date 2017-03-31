package com.example.myapplication.map;

import com.example.myapplication.model.beans.Pair;

/**
 * Created by user on 3/15/17.
 */

public interface MapPresenter {
    void validateData(Pair from, Pair to);

    void attachView(MapView mapView);

    void removeView();
}
