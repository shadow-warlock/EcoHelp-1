package com.example.Activities;

import android.content.Intent;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

import com.google.android.gms.vision.barcode.Barcode;

import androidx.appcompat.widget.Toolbar;
import androidx.core.app.ActivityCompat;


import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.util.Log;
import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;




import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.notbytes.barcode_reader.BarcodeReaderFragment;


import java.util.List;


public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback , BarcodeReaderFragment.BarcodeReaderListener{

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;

    private CameraManager myCamera;

  private ViewGroup mainLayout;
  ImageView flashlight;
    private static final int BARCODE_READER_ACTIVITY_REQUEST = 1208;


    private BarcodeReaderFragment mBarcodeReaderFragment;

    public DecoderActivity() {
    }

    @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    Activity = "Scaner";

    setContentView(R.layout.activity_decoder);
    isOnline(DecoderActivity.this);
    flashlight = findViewById(R.id.flashlight);
      mBarcodeReaderFragment = attachBarcodeReaderFragment();


    mainLayout = findViewById(R.id.main_layout);


    Toolbar toolbar = findViewById(R.id.mytoolbar);
    setSupportActionBar(toolbar);
    Drawer(toolbar,DecoderActivity.this,DecoderActivity.this);
    if (getSupportActionBar() != null) {
      getSupportActionBar().setTitle("Сканер");
    }


  }

    private BarcodeReaderFragment attachBarcodeReaderFragment() {
        final FragmentManager supportFragmentManager = getSupportFragmentManager();
        final FragmentTransaction fragmentTransaction = supportFragmentManager.beginTransaction();
        BarcodeReaderFragment fragment = BarcodeReaderFragment.newInstance(true, false);
        fragment.setListener(this);
        fragmentTransaction.replace(R.id.fm_container, fragment);
        fragmentTransaction.commitAllowingStateLoss();
        return fragment;
    }


    @Override
    public void onScanned(Barcode barcode) {
      String text = barcode.displayValue;
      Log.v("QRCODE =",text);
try {
    Log.v("QRCODE =",text);


    DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
    DatabaseReference coinsUidRef = rootRef.child("coinsUid").child(text);
    mBarcodeReaderFragment.pauseScanning();


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
                                    mBarcodeReaderFragment.resumeScanning();
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
                                            mBarcodeReaderFragment.resumeScanning();

                                        });
                        builder.setPositiveButton("В магазин", (dialog, which) -> {
                            Intent intent = new Intent(DecoderActivity.this, ShopActivity.class);
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
catch (DatabaseException e){
    showDialog("QR Code не верен",DecoderActivity.this);

}

    }

    @Override
    public void onScannedMultiple(List<Barcode> barcodes) {

    }

    @Override
    public void onBitmapScanned(SparseArray<Barcode> sparseArray) {

    }

    @Override
    public void onScanError(String errorMessage) {
      showDialog(errorMessage,DecoderActivity.this);
    }

    @Override
    public void onCameraPermissionDenied() {
      showDialog("Отказано в доступе к камере", DecoderActivity.this);

    }





  @Override
  public boolean onSupportNavigateUp() {
    onBackPressed();  //или this.finish или что то свое
    return true;
  }



  int i = 1;
  public void onClickDecoder(View v) throws CameraAccessException {





    i++;
    if(i %2 != 0){
      flashlight.setImageResource(R.drawable.flashlightoff);
      mBarcodeReaderFragment.setUseFlash(false);
    }
    else {
      flashlight.setImageResource(R.drawable.flashlighton);
      mBarcodeReaderFragment.setUseFlash(true);
    }
  }


}