package com.example.Classes.Dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.fragment.app.DialogFragment;

import com.example.Activities.ShopActivity;
import com.example.ecohelp.R;

public class readBarcodeSuccessDialog extends DialogFragment implements View.OnClickListener {
TextView amount;

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.read_barcode_success,null);
        v.findViewById(R.id.button5).setOnClickListener(this);
        v.findViewById(R.id.textView16).setOnClickListener(this);
        amount = v.findViewById(R.id.textView21);
        Bundle bundle = this.getArguments();
        String coinsAmount = bundle.getString("amount");
        String setText = "Вам начислено " + coinsAmount + " баллов";
        amount.setText(setText);
        return v;
    }

    public void setAmount(String coinsAmount){

    }




    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.button5){
            Intent intent = new Intent(getContext(), ShopActivity.class);
            startActivity(intent);
        }
        else {
            dismiss();
        }

    }
}
