package com.example.Classes;

import android.annotation.SuppressLint;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecohelp.R;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class informarionPandomatsDialog extends DialogFragment {
    List<Pandomats> pandomats;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.information_pandomats, null);
        TextView occupancy = v.findViewById(R.id.occupancy);
        ImageView imagePadomats =  v.findViewById(R.id.imagePandomats);
        TextView moreInformation =  v.findViewById(R.id.moreInformation);
        Bundle bundle = this.getArguments();
        int position = bundle.getInt("someValue", 0); // 5

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
                        String image = pandomats.get(position).getImage();
                        occupancy.setText(trueOccupancy);
                        moreInformation.setText(moreInfo);
                        if(image!=null) {
                            new DownloadImageTask(imagePadomats).execute(image);
                        }
                    }

                    @Override
                    public void onFailure(Call<List<Pandomats>> call, Throwable t) {

                    }

    });
        return v;
}
    @SuppressLint("StaticFieldLeak")
    public class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        ImageView bmImage;

        DownloadImageTask(ImageView bmImage) {
            this.bmImage = bmImage;
        }

        protected Bitmap doInBackground(String... urls) {
            String urldisplay = urls[0];
            Bitmap mIcon11 = null;
            try {
                InputStream in = new java.net.URL(urldisplay).openStream();
                mIcon11 = BitmapFactory.decodeStream(in);
            } catch (Exception e) {
                Log.e("Error", e.getMessage());
                e.printStackTrace();
            }
            return mIcon11;
        }

        protected void onPostExecute(Bitmap result) {
            bmImage.setImageBitmap(result);
        }
    }
}
