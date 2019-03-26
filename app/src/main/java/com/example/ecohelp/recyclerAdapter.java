package com.example.ecohelp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

class recyclerAdapter extends RecyclerView.Adapter<recyclerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Coupons> coupons2;
    recyclerAdapter(Context context, List<Coupons> coupons2){
        this.coupons2 = coupons2;
        this.inflater = LayoutInflater.from(context);
}
@Override
public recyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
    View view = inflater.inflate(R.layout.coupon,parent,false);
    return new ViewHolder(view);
}

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, int position) {
        Coupons coupons = coupons2.get(position);
        viewHolder.imageView.setImageResource(coupons.getLogo());
        viewHolder.nameView.setText(coupons.getName());
        viewHolder.sumView.setText(coupons.getSum());
    }

@Override
public int getItemCount(){
    return coupons2.size();
}
 static class ViewHolder extends RecyclerView.ViewHolder{
    final ImageView imageView;
    final TextView nameView,sumView;
    ViewHolder(View v){
        super(v);
        imageView = (ImageView) v.findViewById(R.id.logo);
        nameView = (TextView) v.findViewById(R.id.name);
        sumView = (TextView)v.findViewById(R.id.sum);
    }
}
}
