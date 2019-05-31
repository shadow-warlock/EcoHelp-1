package com.example.Classes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Activities.MenuActivity;
import com.example.ecohelp.R;

public class ChooseAvatarDialog extends DialogFragment implements View.OnClickListener {


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.choose_avatar_dialog, null);
        v.findViewById(R.id.achievment2).setOnClickListener(this);
        v.findViewById(R.id.achievment1).setOnClickListener(this);
        v.findViewById(R.id.imageView8).setOnClickListener(this);
        v.findViewById(R.id.imageView9).setOnClickListener(this);
        v.findViewById(R.id.imageView11).setOnClickListener(this);
        v.findViewById(R.id.imageView12).setOnClickListener(this);

        return v;

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        Intent intent = new Intent(getActivity(), MenuActivity.class);

        if (i == R.id.achievment2){
            int AvatarNummber = 1;
            intent.putExtra("Avatar",AvatarNummber);
            Log.v("dwdwdwd",""+ AvatarNummber);
            startActivity(intent);
            dismiss();
        }
        if (i == R.id.achievment1){
            int AvatarNummber = 2;
            intent.putExtra("Avatar",AvatarNummber);
            startActivity(intent);
            dismiss();
        }


        if (i == R.id.imageView8){
            int AvatarNummber = 4;
            intent.putExtra("Avatar",AvatarNummber);
            startActivity(intent);
            dismiss();
        }
        if (i == R.id.imageView9){
            int AvatarNummber = 5;
            intent.putExtra("Avatar",AvatarNummber);
            startActivity(intent);
            dismiss();
        }


        if (i == R.id.imageView11){
            int AvatarNummber = 7;
            intent.putExtra("Avatar",AvatarNummber);
            startActivity(intent);
            dismiss();
        }
        if (i == R.id.imageView12){

            int AvatarNummber = 8;

            intent.putExtra("Avatar",AvatarNummber);
            startActivity(intent);
            dismiss();
            Log.v("dwdwd","sahsahsahd8888");
        }



    }
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        Log.d("hiii", "Dialog 1: onDismiss");
    }
}
