package com.example.Activities;

import android.Manifest;
import android.content.pm.PackageManager;
import android.graphics.PointF;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.dlazaro66.qrcodereaderview.QRCodeReaderView;
import com.example.Classes.Pojo.QRCode;
import com.example.Classes.Service;
import com.example.Classes.Dialogs.invalidQrcodeDialog;
import com.example.Classes.Dialogs.readBarcodeSuccessDialog;
import com.google.android.gms.vision.barcode.Barcode;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.SparseArray;
import android.view.SurfaceView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;




import com.example.ecohelp.R;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static android.hardware.Camera.Parameters.FLASH_MODE_AUTO;
import static android.hardware.Camera.Parameters.FLASH_MODE_ON;
import static android.hardware.Camera.Parameters.FLASH_MODE_TORCH;


public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback, QRCodeReaderView.OnQRCodeReadListener {


  List<QRCode> qrCodeList;
    Boolean findSuccess = false;

    DialogFragment dlg1;
    DialogFragment dlg2;

  ImageView flashlight;
    private QRCodeReaderView qrCodeReaderView;
    private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

    private ViewGroup mainLayout;

    public DecoderActivity() {
    }

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Activity = "Scaner";


    setContentView(R.layout.activity_decoder);
    isOnline(DecoderActivity.this);
    flashlight = findViewById(R.id.flashlight);

      dlg2 = new readBarcodeSuccessDialog();
      dlg1 = new invalidQrcodeDialog();
        mainLayout = findViewById(R.id.main_layout);

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CAMERA)
                == PackageManager.PERMISSION_GRANTED) {
            initQRCodeReaderView();

        } else {
            requestCameraPermission();
        }




    Toolbar toolbar = findViewById(R.id.mytoolbar);
    setSupportActionBar(toolbar);
    Drawer(toolbar,DecoderActivity.this,DecoderActivity.this);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Сканер");
    }


  }
    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
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
    public void initQRCodeReaderView(){
        qrCodeReaderView = findViewById(R.id.camera_view);
        qrCodeReaderView.setOnQRCodeReadListener(this);
        qrCodeReaderView.setQRDecodingEnabled(true);
        qrCodeReaderView.setAutofocusInterval(2000L);
        qrCodeReaderView.setBackCamera();
        qrCodeReaderView.startCamera();
    }



    private void requestCameraPermission() {
        if (ActivityCompat.shouldShowRequestPermissionRationale(this, Manifest.permission.CAMERA)) {
            Snackbar.make(mainLayout, "Camera access is required to display the camera preview.",
                    Snackbar.LENGTH_INDEFINITE).setAction("OK", new View.OnClickListener() {
                @Override public void onClick(View view) {
                    ActivityCompat.requestPermissions(DecoderActivity.this, new String[] {
                            Manifest.permission.CAMERA
                    }, MY_PERMISSION_REQUEST_CAMERA);
                }
            }).show();
        } else {
            Snackbar.make(mainLayout, "Permission is not available. Requesting camera permission.",
                    Snackbar.LENGTH_SHORT).show();
            ActivityCompat.requestPermissions(this, new String[] {
                    Manifest.permission.CAMERA
            }, MY_PERMISSION_REQUEST_CAMERA);
        }
    }
    String LastText = "";


  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();  //или this.finish или что то свое
    return true;
  }

  int i = 1;
  public void onClickDecoder(View v) throws CameraAccessException {
      if(qrCodeReaderView!=null) {
          i++;
          if (i % 2 != 0) {
              flashlight.setImageResource(R.drawable.flashlightoff);
              qrCodeReaderView.setTorchEnabled(false);

          } else {
              flashlight.setImageResource(R.drawable.flashlighton);
              qrCodeReaderView.setTorchEnabled(true);
          }
      }
  }


    @Override
    public void onQRCodeRead(String text, PointF[] points) {
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        qrCodeReaderView.setQRDecodingEnabled(false);
        if(!text.equals(LastText)) {
            LastText = text;


            qrCodeList = new ArrayList<>();
            Service.getInstance().getJsonQRCodeApi().loadList().enqueue(new Callback<List<QRCode>>() {
                @Override
                public void onResponse(Call<List<QRCode>> call, Response<List<QRCode>> response) {
                    qrCodeList.addAll(Objects.requireNonNull(response.body()));

                    for (int j = 0; j < qrCodeList.size(); j++) {
                        if (text.equals(qrCodeList.get(j).getKey())) {
                            findSuccess = true;
                            String QrCodeCoinsAmount = qrCodeList.get(j).getValue();
                            DatabaseReference coinsAmountRef = rootRef.child("users").child(getUid()).child("coinsAmount");


                            ValueEventListener valueEventListener = new ValueEventListener() {


                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    long coinsAmount = dataSnapshot.getValue(Long.class);
                                    coinsAmountRef.getRef().setValue(coinsAmount + Integer.parseInt(QrCodeCoinsAmount));
                                    Bundle bundle = new Bundle();
                                    bundle.clear();
                                    bundle.putString("amount", QrCodeCoinsAmount);
                                    dlg2.setArguments(bundle);
                                    dlg2.show(getSupportFragmentManager(), "dlg2");


                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            };
                            coinsAmountRef.addListenerForSingleValueEvent(valueEventListener);
                            break;
                        }

                    }
                    if (!findSuccess) {
                        dlg1.show(getSupportFragmentManager(), "dlg1");

                        qrCodeReaderView.setQRDecodingEnabled(true);


                    }

                }

                @Override
                public void onFailure(Call<List<QRCode>> call, Throwable t) {

                }
            });
        }

}
    }