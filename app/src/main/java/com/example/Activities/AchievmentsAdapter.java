package com.example.Activities;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.Classes.Achievments;
import com.example.ecohelp.R;

import java.util.List;

    public class AchievmentsAdapter extends RecyclerView.Adapter<AchievmentsAdapter.ViewHolder> {
     private LayoutInflater inflater;
    private List<Achievments> achievments;
    public AchievmentsAdapter(Context context, List<Achievments> achievments) {
        this.achievments = achievments;
        this.inflater = LayoutInflater.from(context);
    }


    @NonNull
    @Override
    public AchievmentsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.achivments, parent, false);
        return new AchievmentsAdapter.ViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull AchievmentsAdapter.ViewHolder viewHolder, int position) {
        Achievments achievmentViewHolder = achievments.get(position);
        viewHolder.imageView1.setImageResource(achievmentViewHolder.getImage1());
        viewHolder.imageView2.setImageResource(achievmentViewHolder.getImage2());
        viewHolder.imageView3.setImageResource(achievmentViewHolder.getImage3());


        viewHolder.textView1.setText(achievmentViewHolder.getName1());
        viewHolder.textView2.setText(achievmentViewHolder.getName2());
        viewHolder.textView3.setText(achievmentViewHolder.getName3());


    }

    @Override
    public int getItemCount() {
        return achievments.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView imageView1;
        final ImageView imageView2;
        final ImageView imageView3;

        final TextView textView1;
        final TextView textView2;
        final TextView textView3;



        ViewHolder(View v){
            super(v);
            imageView1 = v.findViewById(R.id.achievment1);
            imageView2 = v.findViewById(R.id.achievment2);
            imageView3 = v.findViewById(R.id.achievment3);

            textView1 = v.findViewById(R.id.achievment1text);
            textView2 = v.findViewById(R.id.achievment2text);
            textView3 = v.findViewById(R.id.achievment3text);

        }
    }
}
