package com.example.Classes;


import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewParent;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.List;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.Classes.Dialogs.barcodeViewDialog;
import com.example.ecohelp.R;



public class LibraryAdapter extends RecyclerView.Adapter<LibraryAdapter.ViewHolder> implements View.OnLongClickListener {
    private LayoutInflater inflater;
    private List<couponsLibrary> couponsLibraryList;
    private Context context;
    private boolean isVisibleCheckBox = false;

    public LibraryAdapter(Context context, List<couponsLibrary> couponsLibraryList){
        this.couponsLibraryList = couponsLibraryList;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.library_coupon,parent,false);
        view.setOnLongClickListener(this);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LibraryAdapter.ViewHolder holder, int position) {
        couponsLibrary couponsLibrary = couponsLibraryList.get(position);
        holder.info.setText(couponsLibrary.getInfoAboutCoupon());
        holder.end.setText(couponsLibrary.getEnd());
        holder.checkBox.setVisibility(isVisibleCheckBox ? View.VISIBLE : View.GONE);
        holder.itemView.setOnClickListener(v -> {
            String i =  couponsLibrary.getTag();
            DialogFragment dlg;
            dlg = new barcodeViewDialog();
            Bundle bundle = new Bundle();
            FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
            bundle.clear();
            bundle.putString("someValue", i);

            dlg.setArguments(bundle);
            dlg.show(fragmentManager,"barcodeview");
        });
    }



    @Override
    public int getItemCount() {
        return couponsLibraryList.size();
    }

    @Override
    public boolean onLongClick(View v) {
        isVisibleCheckBox = true;
        notifyDataSetChanged();
        return false;
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        final TextView end;
        final  TextView info;
        final CheckBox checkBox;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBox = itemView.findViewById(R.id.checkBox2);

            end = itemView.findViewById(R.id.textView20);
            info = itemView.findViewById(R.id.textView22);

        }
    }
}
