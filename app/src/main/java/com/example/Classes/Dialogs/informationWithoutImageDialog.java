package com.example.Classes.Dialogs;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.Classes.Pojo.Pandomats;
import com.example.ecohelp.R;

import java.util.ArrayList;
import java.util.List;

public class informationWithoutImageDialog extends DialogFragment { List<Pandomats> pandomats;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.information_without_image, null);
        TextView occupancy = v.findViewById(R.id.occupancy2);
        TextView moreInformation =  v.findViewById(R.id.moreInfo2);
        Bundle bundle = this.getArguments();
        String Model = bundle.getString("Model");
        String Address = bundle.getString("Address");
        String falseOccupancy = bundle.getString("falseOccupnacy");
        String moreInfo = ("Модель: "+Model+"\n"+ "Адрес: "+ Address);
        String trueOccupancy = "Заполненность: "+falseOccupancy+"%";

        occupancy.setText(trueOccupancy);
        moreInformation.setText(moreInfo);

        pandomats = new ArrayList<>();
        return v;
    }


}
