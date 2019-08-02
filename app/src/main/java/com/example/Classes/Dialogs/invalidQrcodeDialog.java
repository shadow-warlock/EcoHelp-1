package com.example.Classes.Dialogs;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.DialogFragment;

import com.example.ecohelp.R;

public class invalidQrcodeDialog extends DialogFragment implements View.OnClickListener {

    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View v = inflater.inflate(R.layout.invalid_qr_code,null);
        v.findViewById(R.id.textView16).setOnClickListener(this);
        return v;


    }


        @Override
    public void onClick(View v) {

        dismiss();

    }

}
