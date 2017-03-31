package com.example.myapplication.map;

import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.myapplication.R;
import com.example.myapplication.di.DaggerDataComponent;
import com.example.myapplication.di.DataModule;
import com.example.myapplication.model.beans.Pair;
import com.example.myapplication.util.Formatter;
import com.example.myapplication.util.LatLngFactory;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.Circle;
import com.google.android.gms.maps.model.CircleOptions;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.PolylineOptions;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity implements OnMapReadyCallback, MapView {

    private static final String TAG = "MapsActivityTAG_";
    private static final double DEFAULT_RADIUS_SIZE = 20;

    @BindView(R.id.from_latitude)
    EditText editTextFromLatitude;
    @BindView(R.id.from_longitude)
    EditText editTextFromLongitude;
    @BindView(R.id.to_latitude)
    EditText editTextToLatitude;
    @BindView(R.id.to_longitude)
    EditText editTextToLongitude;
    @BindView(R.id.speed)
    EditText editTextSpeed;
    @BindView(R.id.btn)
    Button button;

    private GoogleMap googleMap;
    private Circle circle;

    @Inject
    MapPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);

        ButterKnife.bind(this);

        setupDaggerComponent();
        setupPresenter();
        setupMap();
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        this.googleMap = googleMap;

        this.googleMap.getUiSettings().setCompassEnabled(true);
        this.googleMap.getUiSettings().setZoomControlsEnabled(true);

        // TODO: 3/15/17 Enable all the other EditTexts
        editTextFromLatitude.setEnabled(true);

        // TODO: 3/15/17 Remove on production
        button.callOnClick();
    }

    @OnClick(R.id.btn)
    public void create(View view) {
        presenter.validateData(Formatter.editTextToPair(editTextFromLatitude, editTextFromLongitude),
                Formatter.editTextToPair(editTextToLatitude, editTextToLongitude));
    }

    @Override
    public void showErrorValidation(String error) {
        Snackbar.make(findViewById(R.id.main_view), error, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void paintMarkers(Pair from, Pair to) {
        googleMap.addMarker(new MarkerOptions()
                .position(LatLngFactory.create(from)).title("From"));
        googleMap.addMarker(new MarkerOptions()
                .position(LatLngFactory.create(to)).title("To"));

        circle = googleMap.addCircle(new CircleOptions().center(LatLngFactory.create(from))
                .radius(DEFAULT_RADIUS_SIZE)
                .strokeColor(Color.RED)
                .fillColor(Color.BLUE));
    }

    @Override
    public void moveCamera(Pair destination, float zoomRate) {
        googleMap.moveCamera(CameraUpdateFactory
                .newLatLngZoom(LatLngFactory.create(destination), zoomRate));
    }

    @Override
    public void drawPolyline(List<LatLng> latLngs) {
        googleMap.addPolyline(new PolylineOptions().addAll(latLngs));
    }

    @Override
    public void moveRoute(LatLng latLng, float zoomRate) {
        moveCamera(LatLngFactory.create(latLng), zoomRate);
        moveVehicle(latLng);
    }

    private void moveVehicle(LatLng latLng) {
        circle.setCenter(latLng);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (presenter != null) {
            presenter.removeView();
        }
    }

    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
    }

    private void setupDaggerComponent() {
        DaggerDataComponent.builder()
                .dataModule(new DataModule())
                .build()
                .inject(this);
    }

    private void setupPresenter() {
        presenter.attachView(this);
    }
}
