package com.example.myapplication;

import com.example.myapplication.map.MapPresenterImpl;
import com.example.myapplication.map.MapView;
import com.example.myapplication.model.beans.Pair;
import com.example.myapplication.repository.DataRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

/**
 * Created by user on 3/15/17.
 */

@RunWith(MockitoJUnitRunner.class)
public class MapPresenterImplUnitTest {
    @Mock
    DataRepository dataRepository;

    @Mock
    MapView mapView;

    @Test
    public void shouldInstantiateMapPresenterImpl() throws Exception {
        // TODO: 3/15/17 Complete unit test
        MapPresenterImpl mapPresenter = new MapPresenterImpl(dataRepository);
        mapPresenter.attachView(mapView);

        mapPresenter.validateData(new Pair(10.0, 10.0), new Pair(12.0, 12.0));
    }
}
