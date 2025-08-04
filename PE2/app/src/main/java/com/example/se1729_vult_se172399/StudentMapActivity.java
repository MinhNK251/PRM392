package com.example.se1729_vult_se172399;

import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class StudentMapActivity extends AppCompatActivity implements OnMapReadyCallback {
    
    private GoogleMap mMap;
    private TextView tvStudentInfo;
    private Button btnClose;
    private String studentName;
    private String studentAddress;
    private int studentId;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_student_map);
        
        initViews();
        getIntentData();
        setupMap();
        setupListeners();
    }
    
    private void initViews() {
        tvStudentInfo = findViewById(R.id.tvStudentInfo);
        btnClose = findViewById(R.id.btnClose);
    }
    
    private void getIntentData() {
        studentId = getIntent().getIntExtra("STUDENT_ID", -1);
        studentName = getIntent().getStringExtra("STUDENT_NAME");
        studentAddress = getIntent().getStringExtra("STUDENT_ADDRESS");
        
        if (studentName != null && studentAddress != null) {
            tvStudentInfo.setText(studentName + " - " + studentAddress);
        } else {
            tvStudentInfo.setText("Student Location");
        }
    }
    
    private void setupMap() {
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);
        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }
    
    private void setupListeners() {
        btnClose.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
        
        if (studentAddress != null && !studentAddress.isEmpty()) {
            geocodeAndShowLocation(studentAddress);
        } else {
            LatLng defaultLocation = new LatLng(21.0285, 105.8542);
            mMap.addMarker(new MarkerOptions().position(defaultLocation).title("Default Location"));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
            Toast.makeText(this, "No address provided, showing default location", Toast.LENGTH_SHORT).show();
        }
        
        mMap.getUiSettings().setZoomControlsEnabled(true);
        mMap.getUiSettings().setMapToolbarEnabled(true);
    }
    
    private void geocodeAndShowLocation(String address) {
        Geocoder geocoder = new Geocoder(this, Locale.getDefault());
        
        try {
            List<Address> addresses = geocoder.getFromLocationName(address, 1);
            
            if (addresses != null && !addresses.isEmpty()) {
                Address location = addresses.get(0);
                LatLng studentLocation = new LatLng(location.getLatitude(), location.getLongitude());
                
                String markerTitle = studentName != null ? studentName : "Student Location";
                mMap.addMarker(new MarkerOptions()
                        .position(studentLocation)
                        .title(markerTitle)
                        .snippet(address));
                
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(studentLocation, 15));
                
            } else {
                showDefaultLocationWithMessage("Could not find location for: " + address);
            }
        } catch (IOException e) {
            showDefaultLocationWithMessage("Geocoding failed: " + e.getMessage());
        }
    }
    
    private void showDefaultLocationWithMessage(String message) {
        LatLng defaultLocation = new LatLng(10.8231, 106.6297);
        String markerTitle = studentName != null ? studentName + " (Default Location)" : "Default Location";
        
        mMap.addMarker(new MarkerOptions()
                .position(defaultLocation)
                .title(markerTitle)
                .snippet("Address: " + (studentAddress != null ? studentAddress : "Unknown")));
        
        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(defaultLocation, 12));
        
        Toast.makeText(this, message, Toast.LENGTH_LONG).show();
    }
} 