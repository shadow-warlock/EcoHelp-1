package com.example.Activities;

import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;
import android.os.Bundle;
import androidx.annotation.NonNull;

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
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;




import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.notbytes.barcode_reader.BarcodeReaderFragment;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class DecoderActivity extends BaseActivity
    implements ActivityCompat.OnRequestPermissionsResultCallback , BarcodeReaderFragment.BarcodeReaderListener{

  private static final int MY_PERMISSION_REQUEST_CAMERA = 0;
  List<QRCode> qrCodeList;
    Boolean findSuccess = false;
    private CameraManager myCamera;
    DialogFragment dlg1;
    DialogFragment dlg2;
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
      dlg2 = new readBarcodeSuccessDialog();

        dlg1 = new invalidQrcodeDialog();
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
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        mBarcodeReaderFragment.pauseScanning();

      qrCodeList = new ArrayList<>();
        Service.getInstance().getJsonQRCodeApi().loadList().enqueue(new Callback<List<QRCode>>() {
            @Override
            public void onResponse(Call<List<QRCode>> call, Response<List<QRCode>> response) {
                qrCodeList.addAll(Objects.requireNonNull(response.body()));

                for (int j = 0; j <qrCodeList.size() ; j++) {
                    if(text.equals(qrCodeList.get(j).getKey())) {
                        findSuccess = true;
                        String QrCodeCoinsAmount = qrCodeList.get(j).getValue();
                        DatabaseReference coinsAmountRef = rootRef.child("users").child(getUid()).child("coinsAmount");


                        ValueEventListener valueEventListener = new ValueEventListener() {



                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                long coinsAmount = dataSnapshot.getValue(Long.class);
                                coinsAmountRef.getRef().setValue(coinsAmount + Integer.parseInt(QrCodeCoinsAmount) );
                                Bundle bundle = new Bundle();
                                bundle.clear();
                                bundle.putString("amount",QrCodeCoinsAmount);
                                dlg2.setArguments(bundle);
                                dlg2.show(getSupportFragmentManager(),"dlg2");


                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {

                            }
                        };
                        coinsAmountRef.addListenerForSingleValueEvent(valueEventListener);
                        break;
                    }

                }
                if(!findSuccess){
                dlg1.show(getSupportFragmentManager(),"dlg1");
                mBarcodeReaderFragment.resumeScanning();


                }

            }

            @Override
            public void onFailure(Call<List<QRCode>> call, Throwable t) {

            }
        });



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