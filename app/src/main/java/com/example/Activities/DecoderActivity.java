package com.example.Activities;

import android.Manifest;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.PointF;

import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.vision.CameraSource;
import com.google.android.gms.vision.Detector;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.barcode.BarcodeDetector;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import androidx.appcompat.app.AlertDialog;

import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;



import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.IOException;


public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener{

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;


  private ViewGroup mainLayout;
  ImageView flashlight;

  private SurfaceView cameraView;
  private CameraSource cameraSource;
  String result;

  private QRCodeReaderView qrCodeReaderView;


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Activity = "Scaner";

    setContentView(R.layout.activity_decoder);
    isOnline(DecoderActivity.this);
    flashlight = findViewById(R.id.flashlight);


    mainLayout = findViewById(R.id.main_layout);

    cameraView = findViewById(R.id.cameraView);

    BarcodeDetector detector = new BarcodeDetector.Builder(this).setBarcodeFormats(Barcode.QR_CODE).build();
    cameraSource = new CameraSource.Builder(this, detector).setRequestedPreviewSize(640, 480).build();


    Toolbar toolbar = findViewById(R.id.mytoolbar);
    setSupportActionBar(toolbar);
    Drawer(toolbar, DecoderActivity.this, DecoderActivity.this);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Сканер");
    }


    if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
            == PackageManager.PERMISSION_GRANTED) {
      initQRCodeReaderView();
    } else {
      requestCameraPermission();
    }

    cameraView.getHolder().addCallback(new SurfaceHolder.Callback() {
      @Override
      public void surfaceCreated(SurfaceHolder holder) {
        try {
          cameraSource.start(cameraView.getHolder());
        } catch (IOException e) {
          Log.e("CAMERA SOURCE", e.getMessage());
        }
      }

      @Override
      public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

      }

      @Override
      public void surfaceDestroyed(SurfaceHolder holder) {
        cameraSource.stop();
      }
    });

    detector.setProcessor(new Detector.Processor<Barcode>() {
      @Override
      public void release() {

      }

      @Override
      public void receiveDetections(Detector.Detections<Barcode> detections) {
        final SparseArray<Barcode> barcodes = detections.getDetectedItems();
        if (barcodes.size() != 0) {
          result = barcodes.valueAt(0).displayValue;
        }
      }
    });
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
    View content = getLayoutInflater().inflate(R.layout.activity_decoder, mainLayout, true);

    qrCodeReaderView = content.findViewById(R.id.qrdecoderview);

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

  int i = 1;
  public void onClickDecoder(View v) throws CameraAccessException {
    CameraManager camManager = (CameraManager) getSystemService(Context.CAMERA_SERVICE);
    String cameraId = null; // Usually back camera is at 0 position.
      cameraId = camManager.getCameraIdList()[0];



    i++;
    if(i %2 != 0){
      flashlight.setImageResource(R.drawable.flashlightoff);
      camManager.setTorchMode(cameraId,false);
    }
    else {
      flashlight.setImageResource(R.drawable.flashlighton);
      camManager.setTorchMode(cameraId, true);
    }
  }
}