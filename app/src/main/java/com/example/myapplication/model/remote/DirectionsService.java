package com.example.myapplication.model.remote;

import com.example.myapplication.model.beans.Direction;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by user on 3/15/17.
 */

public interface DirectionsService {
    String API_URL = "https://maps.googleapis.com";

    @GET("/maps/api/directions/json")
    Observable<Direction> getDirections(@Query("origin") String origin,
                                        @Query("destination") String destination,
                                        @Query("key") String key);
}
