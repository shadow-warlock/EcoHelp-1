package com.example.Activities;



import android.os.Bundle;

import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import com.example.Classes.Dialogs.confirmationOfPurchaseCouponsDialog;
import com.example.Classes.ExpandingRecycler.CouponsRecycler;
import com.example.Classes.ExpandingRecycler.ExpandingRecycler;
import com.example.Classes.Pojo.Coupons;
import com.example.Classes.Service;
import com.example.ecohelp.R;


import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class ShopActivity extends BaseActivity  {

    List<Coupons> coupons;
    DialogFragment dlg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);
        Activity = "Shop";


        Toolbar toolbar = findViewById(R.id.mytoolbar);
        setSupportActionBar(toolbar);
        isOnline(ShopActivity.this);
        Drawer(toolbar,ShopActivity.this,ShopActivity.this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle("Магазины");
        }

        dlg = new confirmationOfPurchaseCouponsDialog();
















        //EXPANDING RECYCLER ----- DANGEROUS!!!!!!!!
        List<CouponsRecycler> couponsRecyclerList = null;

        RecyclerView recyclerView = findViewById(R.id.item);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);

        ExpandingRecycler adapter = new ExpandingRecycler(couponsRecyclerList);
        recyclerView.setLayoutManager(layoutManager);
        recyclerView.setAdapter(adapter);


        setInitialData();



    }
    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();  //или this.finish или что то свое
        return true;
    }
    public void setInitialData(){
        coupons = new ArrayList<>();
        Service.getInstance().getJSONCouponsApi().loadList().enqueue(new Callback<List<Coupons>>() {
            @Override
            public void onResponse(Call<List<Coupons>> call, Response<List<Coupons>> response) {
                coupons.addAll(Objects.requireNonNull(response.body()));
                for (int i = 0; i < coupons.size() ; i++) {
                    String nameShop = coupons.get(i).getShopName();
                    String info = coupons.get(i).getShopName();
                    String description = coupons.get(i).getDescription();
                    String start = coupons.get(i).getStart();
                    String end = coupons.get(i).getEnd();
                    Long cost = coupons.get(i).getCost();

                    //Возможно лучше будет добавить Логотип в ресурсы проекта чтобы не грузить вечно картинки с сети
                    String urlLogo = coupons.get(i).getImage();

                    //Тут добавлять элементы в Recycler


                }
            }

            @Override
            public void onFailure(Call<List<Coupons>> call, Throwable t) {

            }
        });
    }


    Boolean haveBarcode;


    //Ниже будет описан метод который в дальнейшем будет использован как onExpandableItemClick и  помещен в ExpandableItemRecyclerAdapter
    public void onClickExpandableItem() {

        Bundle bundle = new Bundle();


        int id = expandableItem.getId;
        int cost = expandableitem.getCost;
        String end = expandableItem.getEnd;
        String info = expandableItem.getInfo;
        String uid = getUid();
        bundle.putString("uid",uid);
        bundle.putString("end",end);
        bundle.putString("info",info);
        bundle.putInt("id",id);
        bundle.putInt("cost",cost);
        dlg.setArguments(bundle);
        dlg.show(getSupportFragmentManager(),"dlg");


    }




}