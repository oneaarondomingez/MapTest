package com.example.myapplication.di;

import com.example.myapplication.map.MapActivity;
import com.example.myapplication.repository.DataRepository;

import dagger.Component;

/**
 * Created by user on 3/15/17.
 */

// TODO: 3/15/17 Break components in sub-components
// TODO: 3/15/17 Set custom scopes
// TODO: 3/15/17 Set custom qualifiers
@Component(modules = DataModule.class)
public interface DataComponent {
    void inject(MapActivity mapActivity);

    void inject(DataRepository dataRepository);
}
