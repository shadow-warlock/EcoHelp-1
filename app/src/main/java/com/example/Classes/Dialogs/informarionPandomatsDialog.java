package com.example.Classes.Dialogs;

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

import com.example.Classes.Pojo.Pandomats;
import com.example.ecohelp.R;

import java.io.InputStream;
import java.util.List;

public class informarionPandomatsDialog extends DialogFragment {
    List<Pandomats> pandomats;

    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        getDialog().setTitle("Title!");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.information_pandomats, null);
        TextView occupancy = v.findViewById(R.id.occupancy);
        ImageView imagePadomats =  v.findViewById(R.id.imagePandomats);
        TextView moreInformation =  v.findViewById(R.id.moreInformation);
        Bundle bundle = this.getArguments();
        String image = bundle.getString("image");
        String Model = bundle.getString("Model");
        String Address = bundle.getString("Address");
        String falseOccupancy = bundle.getString("falseOccupnacy");
        int trueOccupancy  = Integer.parseInt(falseOccupancy);
        String moreInfo = ("Модель: "+Model+"\n"+ "Адрес: "+ Address);
        String occupancyWithProcent = falseOccupancy+"%";


        occupancy.setText(occupancyWithProcent);
        if(trueOccupancy<=70){
            occupancy.setTextColor(getResources().getColor(R.color.color_green));
        }
        else if(trueOccupancy<90){
            occupancy.setTextColor(getResources().getColor(R.color.yellow));
        }
        else{
            occupancy.setTextColor(getResources().getColor(R.color.red));
        }


        moreInformation.setText(moreInfo);
        if(image!=null) {
            new DownloadImageTask(imagePadomats).execute(image);
        }
        else {
            imagePadomats.setImageResource(R.drawable.d6x8oayzi6a);
        }

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
