package com.example.Activities;

import android.content.Context;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

import android.util.Log;


import androidx.appcompat.widget.Toolbar;



import com.example.Classes.Pojo.Pandomats;
import com.example.Classes.Service;
import com.example.Classes.Dialogs.informarionPandomatsDialog;
import com.example.Classes.Dialogs.informationWithoutImageDialog;
import com.example.ecohelp.R;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class MenuActivity extends BaseActivity
        implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {


    List<Pandomats> pandomats;
    private List<LatLng> places = new ArrayList<>();
Context context = MenuActivity.this;
    DialogFragment dlg1;
    Toolbar toolbar;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Activity = "Menu";
        isOnline(MenuActivity.this);

        setContentView(R.layout.activity_menu);
        toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);

        Drawer(toolbar,MenuActivity.this,MenuActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Аппараты");
        }


        dlg1 = new informarionPandomatsDialog();
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        assert mapFragment != null;
        mapFragment.getMapAsync(this);
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(getUid());
        int count = getIntent().getIntExtra("Avatar", 0);
        Log.v("dwdwdwdwd", "" + count);

        if (count != 0) {

            ValueEventListener valueEventListener = new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                    uidRef.child("Avatar").setValue("" + count);
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            };
            uidRef.addListenerForSingleValueEvent(valueEventListener);


        }
    }



    public void onMapReady(GoogleMap googleMap) {
        googleMap.setOnMarkerClickListener(this);


        pandomats = new ArrayList<>();
        Service.getInstance().getJSONAPlaceApi()
                .loadList()
                .enqueue(new Callback<List<Pandomats>>() {


                    @Override
                    public void onResponse(@NonNull Call<List<Pandomats>> call, @NonNull Response<List<Pandomats>> response) {
                        Log.v("SUCCESS", "SUCCESS");
                        pandomats.addAll(Objects.requireNonNull(response.body()));


                        MarkerOptions[] markers = new MarkerOptions[pandomats.size()];
                        for (int i = 0; i < pandomats.size(); ) {
                            Double latitude = pandomats.get(i).getLatitude();
                            Double longitude = pandomats.get(i).getLongitude();
                            String falseOccupancy = pandomats.get(i).getLastDeviceData().getOccupancy().getValue();
                            int intOccupancy = Integer.parseInt(falseOccupancy);
                            places.add(new LatLng(latitude, longitude));
                            if (intOccupancy <= 70) {
                                markers[i] = new MarkerOptions().position(places.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN));
                            }
                            if (intOccupancy > 70 && intOccupancy < 90) {
                                markers[i] = new MarkerOptions().position(places.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_YELLOW));
                            }
                            if (intOccupancy >= 90) {
                                markers[i] = new MarkerOptions().position(places.get(i)).icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
                            }
                            googleMap.addMarker(markers[i]);


                            i++;


                        }



                    }

                    @Override
                    public void onFailure(@NonNull Call<List<Pandomats>> call, @NonNull Throwable t) {

                    }
                });




    }
    DialogFragment dlg2;


    @Override
    public boolean onMarkerClick(Marker marker) {
        String i = marker.getId();
        Bundle bundle = new Bundle();
        int x = Integer.parseInt(i.substring(1));
        String Model = pandomats.get(x).getModel();
        String Address = pandomats.get(x).getAddress();
        String falseOccupancy = pandomats.get(x).getLastDeviceData().getOccupancy().getValue();
        if(pandomats.get(x).getImage() !=null) {

            bundle.clear();
            dlg1 = new informarionPandomatsDialog();
            bundle.putString("Address",Address);
            bundle.putString("Model",Model);
            bundle.putString("falseOccupnacy",falseOccupancy);
            String image = pandomats.get(x).getImage();
            bundle.putString("image",image);
            dlg1.setArguments(bundle);
            dlg1.show(getSupportFragmentManager(), "wdw");
        }
        else {
            bundle.clear();
            dlg2 = new informationWithoutImageDialog();
            bundle.putString("Address",Address);
            bundle.putString("Model",Model);
            bundle.putString("falseOccupnacy",falseOccupancy);

            dlg2.setArguments(bundle);
            dlg2.show(getSupportFragmentManager(),"wdw");


        }

        return true;
    }
}