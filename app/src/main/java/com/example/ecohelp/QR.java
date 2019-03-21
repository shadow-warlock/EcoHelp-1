package com.example.ecohelp;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;

public class QR extends Activity implements View.OnClickListener {
static {
  System.loadLibrary("iconv");
}
  public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_qr);



    }

  @Override
  public void onClick(View v) {

  }
}
