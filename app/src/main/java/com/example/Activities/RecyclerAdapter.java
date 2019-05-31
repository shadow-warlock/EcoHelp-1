package com.example.Activities;

        import android.content.Context;

        import android.os.Bundle;
        import android.support.annotation.NonNull;
        import android.support.v4.app.DialogFragment;
        import android.support.v4.app.FragmentManager;
        import android.support.v7.app.AppCompatActivity;
        import android.support.v7.widget.RecyclerView;
        import android.util.Log;
        import android.view.LayoutInflater;
        import android.view.View;
        import android.view.ViewGroup;
        import android.widget.ImageView;


        import com.example.Classes.Coupons;
        import com.example.Classes.barcodeView;
        import com.example.ecohelp.R;

        import java.util.List;

class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> implements View.OnClickListener {

    private Context context;
    private LayoutInflater inflater;
    private List<Coupons> coupons2;
     RecyclerAdapter(Context context, List<Coupons> coupons2){
        
        this.coupons2 = coupons2;
        this.inflater = LayoutInflater.from(context);
        this.context = context;

    }


    @NonNull
    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType){
        View view = inflater.inflate(R.layout.coupon,parent,false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, final int position) {
        final Coupons coupons = coupons2.get(position);

        viewHolder.imageView.setImageResource(coupons.getLogo());
        viewHolder.imageView.setOnClickListener(v -> {
            String i = coupons.getTAG();
            DialogFragment dlg;
            dlg = new barcodeView();

            Log.v("dwdwdw",""+i);
            Bundle bundle = new Bundle();
            if (i.equals("lentaAmount500")){

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "5000112592702");

                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");
            }
            if (i.equals("lentaAmount300")){

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "0000054490086");
                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");
            }
            if (i.equals("lentaAmount100")){
                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "0000054491472");
                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");
            }
            if (i.equals("petiarochkaAmount500")){

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "0000054492387");
                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");
            }
            if (i.equals("petiarochkaAmount300")){

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "0049000065206");
                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");
            }
            if (i.equals("petiarochkaAmount100")){

                FragmentManager fragmentManager = ((AppCompatActivity)context).getSupportFragmentManager();
                bundle.clear();
                bundle.putString("someValue", "050051000481");
                dlg.setArguments(bundle);
                dlg.show(fragmentManager,"barcodeview");

            }


        });
    }


    @Override
    public int getItemCount(){
        return coupons2.size();
    }

    @Override
    public void onClick(final View v) {


    }

    static class ViewHolder extends RecyclerView.ViewHolder{
        final ImageView imageView;

        ViewHolder(View v){
            super(v);
            imageView = v.findViewById(R.id.logo);
        }
    }
}
