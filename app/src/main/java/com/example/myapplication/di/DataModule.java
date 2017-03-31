package com.example.myapplication.di;

import com.example.myapplication.map.MapPresenter;
import com.example.myapplication.map.MapPresenterImpl;
import com.example.myapplication.model.remote.DirectionsFactory;
import com.example.myapplication.model.remote.DirectionsService;
import com.example.myapplication.repository.DataRepository;

import dagger.Module;
import dagger.Provides;
import retrofit2.Retrofit;

/**
 * Created by user on 3/15/17.
 */

@Module
public class DataModule {

    @Provides
    public DataRepository provideDataRepository() {
        return new DataRepository();
    }

    @Provides
    public MapPresenter provideMapPresenterImpl(DataRepository dataRepository) {
        return new MapPresenterImpl(dataRepository);
    }

    @Provides
    public Retrofit provideRetrofit() {
        return DirectionsFactory.create();
    }

    @Provides
    public DirectionsService provideDirectionsService(Retrofit retrofit) {
        return retrofit.create(DirectionsService.class);
    }
}
