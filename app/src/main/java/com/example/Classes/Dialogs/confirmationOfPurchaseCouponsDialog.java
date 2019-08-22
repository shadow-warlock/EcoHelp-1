package com.example.Classes.Dialogs;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;



import com.example.Classes.Pojo.BarCode;
import com.example.Classes.Service;
import com.example.ecohelp.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;



public class confirmationOfPurchaseCouponsDialog extends DialogFragment implements View.OnClickListener {
    private Boolean haveBarcode;
     private String info;
    private String end;
    private String uid;
    private Long id;
    private Long cost;
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.confirmation_of_purchase_coupons,null);

        TextView infoTextView = v.findViewById(R.id.info);
        TextView costTextView = v.findViewById(R.id.cost);
        Bundle bundle = this.getArguments();
         info = bundle.getString("info");
         end = bundle.getString("end");
         uid = bundle.getString("uid");
         id = bundle.getLong("id");
         cost = bundle.getLong("cost");
        String trueInfo = info+"\n действительна до"+end;
        String trueCost = cost+" баллов";
        infoTextView.setText(trueInfo);
        costTextView.setText(trueCost);
        v.findViewById(R.id.button6).setOnClickListener(this);






        return v;
    }

    @Override
    public void onClick(View v) {
        try {

           //Проверяю есть штрихкод на этот купон
            Service.getInstance().getJSONGetBarcodeByCouponApi().getBarcodeWithId(id).enqueue(new Callback<BarCode>() {

                @Override
                public void onResponse(Call<BarCode> call, Response<BarCode> response) {

                    haveBarcode = true;
                }

                @Override
                public void onFailure(Call<BarCode> call, Throwable t) {

                }
            });
        }
        catch (Exception e){
            haveBarcode = false;

        }






        DatabaseReference rootRef = FirebaseDatabase.getInstance().getReference();
        DatabaseReference uidRef = rootRef.child("users").child(uid);
        ValueEventListener valueEventListener = new ValueEventListener() {
            //Покупка купона

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
               //NumberCoupons обозначает количество всех купонов у пользователя
                Long numberCoupons =  dataSnapshot.child("Coupons").child("NumberCoupons").getValue(Long.class);



                Long coinsAmount = dataSnapshot.getValue(Long.class);
                if (coinsAmount >= cost && haveBarcode) {


                    Long trueNumberCoupons = numberCoupons+1;

                    uidRef.child("Coupons").child("NumberCoupons").setValue(trueNumberCoupons);
                    uidRef.child("coinsAmount").setValue(coinsAmount - cost);
                    uidRef.child("Coupons").child(String.valueOf(trueNumberCoupons)).child("id").setValue(id);
                    uidRef.child("Coupons").child(String.valueOf(trueNumberCoupons)).child("end").setValue(end);
                    uidRef.child("Coupons").child(String.valueOf(trueNumberCoupons)).child("info").setValue(info);

                }
                else if(!haveBarcode){
                    Toast toast = Toast.makeText(getActivity(),
                            "Купоны закончились", Toast.LENGTH_LONG);
                    toast.show();
                }
                else{
                    Toast toast = Toast.makeText(getActivity(),
                            "У вас недостаточно баллов", Toast.LENGTH_LONG);
                    toast.show();

                }
            }


            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }


        };
        uidRef.addListenerForSingleValueEvent(valueEventListener);


    }
}
