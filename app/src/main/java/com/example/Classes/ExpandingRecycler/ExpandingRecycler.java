package com.example.Classes.ExpandingRecycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.ecohelp.R;
import com.thoughtbot.expandablerecyclerview.ExpandableRecyclerViewAdapter;
import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class ExpandingRecycler extends ExpandableRecyclerViewAdapter<LogoHolder, infoHolder> {
    LayoutInflater inflater;
    public ExpandingRecycler(List<? extends ExpandableGroup> groups) {

        super(groups);
    }

    @Override
    public LogoHolder onCreateGroupViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_logo, parent, false);
        return new LogoHolder(view);
    }

    @Override
    public infoHolder onCreateChildViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.list_info,parent,false);
        return new infoHolder(view);
    }

    @Override
    public void onBindChildViewHolder(infoHolder holder, int flatPosition, ExpandableGroup group, int childIndex) {
        final infoCoupons infoCoupons = (infoCoupons) group.getItems().get(childIndex);
        holder.setInfo(infoCoupons.getInfo());
    }

    @Override
    public void onBindGroupViewHolder(LogoHolder holder, int flatPosition, ExpandableGroup group) {
        holder.setLogoCoupons(group);

    }
}
