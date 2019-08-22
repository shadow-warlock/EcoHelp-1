package com.example.Classes.ExpandingRecycler;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.Classes.Pojo.Coupons;
import com.example.Classes.Service;
import com.example.ecohelp.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ExpandingRecycler extends ExpandableRecyclerViewAdapter<LogoHolder, infoHolder> {
    public ExpandingRecycler(List<? extends ExpandableGroup> groups) {

        super(groups);
    }

    @Override
    public LogoHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_logo, parent, false);
        return new LogoHolder(view);
    }

    @Override
    public infoHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.list_info,parent,false);
        return new infoHolder(view);
    }


    @Override
    public void onBindChildViewHolder(infoHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final InfoCoupons infoCoupons = (InfoCoupons) group.getItems().get(childIndex);
        holder.setInfo(infoCoupons.getInfo());
        holder.setDate(infoCoupons.getDate());

    }

    @Override
    public void onBindGroupViewHolder(LogoHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setLogoCoupons(group);

    }
}
