package com.example.Activities;

        import android.content.Context;
        import android.support.annotation.NonNull;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;


        import com.example.Classes.Coupons;
        import com.example.ecohelp.R;

        import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {
    private LayoutInflater inflater;
    private List<Coupons> coupons2;
    RecyclerAdapter(Context context, List<Coupons> coupons2){
        this.coupons2 = coupons2;
        this.inflater = LayoutInflater.from(context);
    }
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.coupon,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int position) {
        Coupons coupons = coupons2.get(position);
        viewHolder.imageView.setImageResource(coupons.getLogo());
        viewHolder.imageView.setOnClickListener(v -> Log.d("aaaa","aaaaa"));

    }

    @Override
    public int getItemCount(){
        return coupons2.size();
    }
    static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView imageView;

        ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.logo);
        }
    }
}
