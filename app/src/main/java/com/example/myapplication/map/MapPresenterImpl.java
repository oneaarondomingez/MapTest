package com.example.myapplication.map;

import com.example.myapplication.model.beans.Direction;
import com.example.myapplication.model.beans.Pair;
import com.example.myapplication.model.beans.Route;
import com.example.myapplication.repository.DataRepository;
import com.example.myapplication.util.InputValidator;
import com.google.android.gms.maps.model.LatLng;
import com.google.maps.android.PolyUtil;

import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.functions.Function;
import io.reactivex.internal.util.AppendOnlyLinkedArrayList;

/**
 * Created by user on 3/15/17.
 */

public class MapPresenterImpl implements MapPresenter, Observer<Direction> {
    private static final float DEFAULT_ZOOM_MAP = 14.5f;
    private static final String OK_STATUS = "OK";

    private MapView view;

    private DataRepository dataRepository;

    // TODO: 3/15/17 Implement movement of the map by speed
    private double speed;

    private CompositeDisposable compositeDisposable;

    public MapPresenterImpl(DataRepository dataRepository) {
        this.dataRepository = dataRepository;

        compositeDisposable = new CompositeDisposable();
    }

    @Override
    public void validateData(Pair from, Pair to) {
        if (!InputValidator.validateInput(from, to)) {
            view.showErrorValidation("Wrong validation going on!");
            return;
        }

        compositeDisposable.clear();
        dataValidated(from, to);
    }

    @Override
    public void attachView(MapView view) {
        this.view = view;
    }

    @Override
    public void removeView() {
        this.view = null;
    }

    private void dataValidated(Pair from, Pair to) {
        view.paintMarkers(from, to);
        view.moveCamera(from, DEFAULT_ZOOM_MAP);

        dataRepository.getDirections(from.generateCoordinate(), to.generateCoordinate(), this);
    }

    @Override
    public void onSubscribe(Disposable d) {
        // TODO: 3/15/17 Show Progress on View
        System.out.println("onSubscribe: " + d);
    }

    @Override
    public void onNext(final Direction direction) {
        System.out.println("onNext:" + direction);
        if (direction.getStatus().equals(OK_STATUS)) {
            Disposable disposable = Observable.just(direction.getRoutes())
                    .filter(routes -> routes != null && !routes.isEmpty())
                    .map(routes -> routes.get(0))
                    .doOnNext(route -> view.drawPolyline(decodeRoute(route))) //Drawing the whole route
                    .flatMap(route -> Observable.fromIterable(decodeRoute(route)))
                    .concatMap(latLng -> Observable.just(latLng).delay(1, TimeUnit.SECONDS)) //Generate 1 sec delay
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(latLng -> view.moveRoute(latLng, DEFAULT_ZOOM_MAP)); //Moving the vehicle with a 1 sec delay through the route

            compositeDisposable.add(disposable);
        }
    }

    @Override
    public void onError(Throwable e) {
        // TODO: 3/15/17 Show Error on View
        System.out.println("onError: " + e.toString());
    }

    @Override
    public void onComplete() {
        // TODO: 3/15/17 Hide Progress on View
        System.out.println("onComplete: ");
    }

    private List<LatLng> decodeRoute(Route route) {
        return PolyUtil.decode(route.getOverviewPolyline().getPoints());
    }
}
