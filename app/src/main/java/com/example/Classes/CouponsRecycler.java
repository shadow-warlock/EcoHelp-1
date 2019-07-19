package com.example.Classes;

import com.thoughtbot.expandablerecyclerview.models.ExpandableGroup;

import java.util.List;

public class CouponsRecycler extends ExpandableGroup<infoCoupons> {
    public CouponsRecycler(String title, List items) {
        super(title, items);
    }
}
