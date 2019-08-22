package com.example.Classes.Dialogs;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class ChooseAvatarDialog extends DialogFragment implements View.OnClickListener {
    private int AvatarNummber;
    private String uID;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Выбор аватара");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.choose_avatar_dialog, null);
        v.findViewById(R.id.avatar1).setOnClickListener(this);
        v.findViewById(R.id.avatar2).setOnClickListener(this);
        Bundle bundle = this.getArguments();
        uID = bundle.getString("uid");
        Log.v("UIDDDDDDD",uID);

        return v;

    }

    @Override
    public void onClick(View v) {
        int i = v.getId();
        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();

        DatabaseReference uidRef = rootRef.child("users").child(uID);
        if (i == R.id.avatar1){
             AvatarNummber = 1;

        }
        if (i == R.id.avatar2){
             AvatarNummber = 2;

        }
        ValueEventListener valueEventListener = new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                uidRef.child("Avatar").setValue("" + AvatarNummber);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);
        dismiss();

    }
    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);

        Log.d("hiii", "Dialog 1: onDismiss");
    }
}
