package com.example.Activities;

import android.Manifest;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.res.ColorStateList;
import android.graphics.PointF;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v4.app.ActivityCompat;


import android.support.v4.widget.CompoundButtonCompat;
import android.support.v7.app.AlertDialog;

import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;




import com.example.Classes.PointsOverlayView;
import com.example.QrCodeReader.QRCodeReaderView;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;



public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener {

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;


  private ViewGroup mainLayout;



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
  public boolean onSupportNavigateUp() {
    onBackPressed();  //или this.finish или что то свое
    return true;
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
    qrCodeReaderView.setQRDecodingEnabled(false);





  DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
  DatabaseReference coinsUidRef = rootRef.child("coinsUid").child(text);


  ValueEventListener valueEventListener = new ValueEventListener() {
    @SuppressWarnings("ConstantConditions")
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
      Log.v(text, "" + dataSnapshot);
      if (!dataSnapshot.exists()) {
        AlertDialog.Builder builder = new AlertDialog.Builder(DecoderActivity.this);
        builder.setTitle("Информация")
                .setMessage("QR-код недействителен")
                .setCancelable(false)
                .setNegativeButton("ОК",
                        (dialog, id) -> {
                  qrCodeReaderView.setQRDecodingEnabled(true);
                        });
        final AlertDialog alert = builder.create();


        alert.show();


      } else {
        long qrCoinsAmount = dataSnapshot.getValue(Long.class);
        DatabaseReference coinsAmountRef = rootRef.child("users").child(getUid()).child("coinsAmount");
        ValueEventListener valueEventListener = new ValueEventListener() {


          @Override
          public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            long coinsAmount = dataSnapshot.getValue(Long.class);
            coinsAmountRef.getRef().setValue(coinsAmount + qrCoinsAmount);


            AlertDialog.Builder builder = new AlertDialog.Builder(DecoderActivity.this);
            builder.setTitle("Оповещение")
                    .setMessage("Вам начислено " + qrCoinsAmount + " баллов")
                    .setCancelable(false)
                    .setNegativeButton("ОК",
                            (dialog, id) -> {
                              qrCodeReaderView.setQRDecodingEnabled(true);

                            });
            builder.setPositiveButton("В магазин", (dialog, which) -> {
              Intent intent = new Intent(DecoderActivity.this,ShopActivity.class);
              startActivity(intent);
              finish();

            });

            AlertDialog alert = builder.create();
            Log.v("dssdwd", "" + alert.isShowing());
            alert.show();


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
    flashlightCheckBox.setOnCheckedChangeListener((compoundButton, isChecked) -> qrCodeReaderView.setTorchEnabled(isChecked));




    pointsOverlayView = content.findViewById(R.id.points_overlay_view);


    qrCodeReaderView.setAutofocusInterval(2000L);
    qrCodeReaderView.setOnQRCodeReadListener(this);
    qrCodeReaderView.setBackCamera();

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


}