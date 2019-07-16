package com.example.Classes;

import android.annotation.SuppressLint;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ecohelp.R;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class informationWithoutImageDialog extends DialogFragment { List<Pandomats> pandomats;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.information_without_image, null);
        TextView occupancy = v.findViewById(R.id.occupancy2);
        TextView moreInformation =  v.findViewById(R.id.moreInfo2);
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("someValue", 0);

        pandomats = new ArrayList<>();
        Service.getInstance()
                .getJSONAPlaceApi()
                .loadList()
                .enqueue(new Callback<List<Pandomats>>() {

                    @Override
                    public void onResponse(Call<List<Pandomats>> call, Response<List<Pandomats>> response) {
                        pandomats.addAll(response.body());
                        String Model = pandomats.get(position).getModel();
                        String Address = pandomats.get(position).getAddress();
                        String falseOccupancy = pandomats.get(position).getLastDeviceData().getOccupancy().getValue();
                        String moreInfo = ("Модель: "+Model+"\n"+ "Адрес: "+ Address);
                        String trueOccupancy = "Заполненность: "+falseOccupancy+"%";
                        occupancy.setText(trueOccupancy);
                        moreInformation.setText(moreInfo);
                    }

                    @Override
                    public void onFailure(Call<List<Pandomats>> call, Throwable t) {

                    }

                });
        return v;
    }


}
