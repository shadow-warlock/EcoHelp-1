package com.example.Activities;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import android.widget.TextView;
import android.widget.Toast;

import com.example.Classes.PointsOverlayView;
import com.example.QrCodeReader.QRCodeReaderView;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.concurrent.TimeUnit;

public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener {

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

  private ViewGroup mainLayout;

  private TextView resultTextView;
  private QRCodeReaderView qrCodeReaderView;
  private PointsOverlayView pointsOverlayView;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    setContentView(R.layout.activity_decoder);

    mainLayout = findViewById(R.id.main_layout);

    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
      initQRCodeReaderView();
    } else {
      requestCameraPermission();
    }
  }

  @Override
  protected void onResume() {
    super.onResume();

    if (qrCodeReaderView != null) {
      qrCodeReaderView.startCamera();
    }
  }

  @Override
  protected void onPause() {
    super.onPause();

    if (qrCodeReaderView != null) {
      qrCodeReaderView.stopCamera();
    }
  }


  // Called when a QR is decoded
  // "text" : the text encoded in QR
  // "points" : points where QR control points are placed
  @Override
  public void onQRCodeRead(String text, PointF[] points) {
    pointsOverlayView.setPoints(points);

    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference coinsUidRef = rootRef.child("coinsUid").child(text);


    ValueEventListener valueEventListener = new ValueEventListener() {
      @SuppressWarnings("ConstantConditions")
      @Override
      public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        Log.v(text,""+dataSnapshot);
        if (!dataSnapshot.exists()) {


          ShowToastTask toastTask = new ShowToastTask();
          toastTask.execute();






        }
        else {
          long qrCoinsAmount = dataSnapshot.getValue(Long.class);
          DatabaseReference coinsAmountRef = rootRef.child("users").child(getUid()).child("coinsAmount");
          ValueEventListener valueEventListener = new ValueEventListener() {


            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              long coinsAmount = dataSnapshot.getValue(Long.class);
              coinsAmountRef.getRef().setValue(coinsAmount + qrCoinsAmount);
              coinsUidRef.removeValue();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
          };
          coinsAmountRef.addListenerForSingleValueEvent(valueEventListener);


        }


      }

      @Override
      public void onCancelled(@NonNull DatabaseError databaseError) {

      }

    };
    coinsUidRef.addListenerForSingleValueEvent(valueEventListener);

    }


  private void requestCameraPermission() {
    if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
      Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
          Snackbar.LENGTH_INDEFINITE).setAction("OK", view -> ActivityCompat.requestPermissions(DecoderActivity.this, new String[] {
              Manifest.permission.CAMERA
          }, MY_PERMISSION_REQUEST_CAMERA)).show();
    } else {
      Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
          Snackbar.LENGTH_SHORT).show();
      ActivityCompat.requestPermissions(this, new String[] {
          Manifest.permission.CAMERA
      }, MY_PERMISSION_REQUEST_CAMERA);
    }
  }

  private void initQRCodeReaderView() {
    View content = getLayoutInflater().inflate(R.layout.content_decoder, mainLayout, true);

    qrCodeReaderView = content.findViewById(R.id.qrdecoderview);

    CheckBox flashlightCheckBox = content.findViewById(R.id.flashlight_checkbox);

    pointsOverlayView = content.findViewById(R.id.points_overlay_view);


    qrCodeReaderView.setAutofocusInterval(2000L);
    qrCodeReaderView.setOnQRCodeReadListener(this);
    qrCodeReaderView.setBackCamera();
    flashlightCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> qrCodeReaderView.setTorchEnabled(isChecked));
    qrCodeReaderView.setQRDecodingEnabled(true);
    qrCodeReaderView.startCamera();
  }
  @Override public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                                   @NonNull int[] grantResults) {
    if (requestCode != MY_PERMISSION_REQUEST_CAMERA) {
      return;
    }

    if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
      Snackbar.make(mainLayout, "Camera permission was granted.", Snackbar.LENGTH_SHORT).show();
      initQRCodeReaderView();
    } else {
      Snackbar.make(mainLayout, "Camera permission request was denied.", Snackbar.LENGTH_SHORT)
              .show();
    }
  }
  class ShowToastTask extends AsyncTask<Void, Void, Void> {

    @Override
    protected Void doInBackground(Void... voids) {
      try{


          TimeUnit.SECONDS.sleep(5);
          runOnUiThread(() -> {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "QRCode неверен! Попробуйте ещё раз", Toast.LENGTH_SHORT);
            toast.show();
          });



      }catch (InterruptedException e){
        e.printStackTrace();
      }

      return null;
    }

  }
}