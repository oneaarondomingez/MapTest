package com.example.myapplication.repository;

import com.example.myapplication.di.DaggerDataComponent;
import com.example.myapplication.di.DataModule;
import com.example.myapplication.model.beans.Direction;
import com.example.myapplication.model.remote.DirectionsService;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by user on 3/15/17.
 */

public class DataRepository {
    // TODO: 3/15/17 Save directions on database
    // TODO: 3/15/17 Hide the API_KEY with the NDK (and obviously don't upload it to VC)

    private static final String API_KEY = "AIzaSyA0T8lhdlqiiRQi2gEDSDsss0JBewd1Df8";

    @Inject
    DirectionsService directionsService;

    public DataRepository() {
        setupDaggerRepository();
    }

    private void setupDaggerRepository() {
        DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build()
                .inject(this);
    }

    public void getDirections(String from, String to, Observer<Direction> directionObserver) {
        directionsService.getDirections(from, to, API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(directionObserver);
    }
}
