package com.example.Classes;

import android.annotation.SuppressLint;
import android.content.DialogInterface;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.ecohelp.R;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;

import java.util.EnumMap;
import java.util.Map;

import static android.graphics.Color.BLACK;
import static android.graphics.Color.WHITE;

public class barcodeView extends DialogFragment implements View.OnClickListener {

ImageView barcodeview;
TextView barcodetextView;
    String barcode_data;
    // barcode image
    Bitmap bitmap = null;


    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getDialog().setTitle("Ваш штрихкод");
        @SuppressLint("InflateParams") View v = inflater.inflate(R.layout.barcode_view, null);
        Bundle bundle = this.getArguments();
        assert bundle != null;
        barcode_data = bundle.getString("someValue");
        barcodetextView = v.findViewById(R.id.textViewbarcode);
        barcodeview =  v.findViewById(R.id.barcodeView);

        try {
            bitmap = encodeAsBitmap(barcode_data);
            barcodeview.setImageBitmap(bitmap);
            barcodetextView.setText(barcode_data);
        } catch (WriterException e) {
            e.printStackTrace();
        }


        return v;

    }
    Bitmap encodeAsBitmap(String contents) throws WriterException {
        if (contents == null) {
            return null;
        }
        Map<EncodeHintType, Object> hints = null;
        String encoding = guessAppropriateEncoding(contents);
        if (encoding != null) {
            hints = new EnumMap<>(EncodeHintType.class);
            hints.put(EncodeHintType.CHARACTER_SET, encoding);
        }
        MultiFormatWriter writer = new MultiFormatWriter();
        BitMatrix result;
        try {
            result = writer.encode(contents, BarcodeFormat.CODE_128, 600, 300, hints);
        } catch (IllegalArgumentException iae) {
            // Unsupported format
            return null;
        }
        int width = result.getWidth();
        int height = result.getHeight();
        int[] pixels = new int[width * height];
        for (int y = 0; y < height; y++) {
            int offset = y * width;
            for (int x = 0; x < width; x++) {
                pixels[offset + x] = result.get(x, y) ? BLACK : WHITE;
            }
        }

        Bitmap bitmap = Bitmap.createBitmap(width, height,
                Bitmap.Config.ARGB_8888);
        bitmap.setPixels(pixels, 0, width, 0, 0, width, height);
        return bitmap;
    }

    private static String guessAppropriateEncoding(CharSequence contents) {
        // Very crude at the moment
        for (int i = 0; i < contents.length(); i++) {
            if (contents.charAt(i) > 0xFF) {
                return "UTF-8";
            }
        }
        return null;
    }


    public void onDismiss(DialogInterface dialog) {
        super.onDismiss(dialog);
       barcodeview = null;
       barcodetextView = null;
       barcode_data = null;
       Log.v("dwdw","wdwdwd");


    }



    @Override
    public void onClick(View v) {
        dismiss();

    }
}
