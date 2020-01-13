package com.poc.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.poc.myapplication.R;

import java.util.ArrayList;

public class SelectedAdapter extends BaseAdapter {

    private Context context;
    private ArrayList<Integer> cards;
    private LayoutInflater inflter;

    public SelectedAdapter(Context applicationContext, ArrayList<Integer> pCards) {
        this.context = applicationContext;
        this.cards = pCards;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cards.size();
    }

    @Override
    public Object getItem(int i) {
        return null;
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        view = inflter.inflate(R.layout.row_card, null); // inflate the layout
        ImageView icon = view.findViewById(R.id.row_card); // get the reference of ImageView
        view.setLayoutParams(new GridView.LayoutParams(GridView.AUTO_FIT, 80));
        icon.setImageResource(cards.get(i)); // set logo images
        return view;
    }
}
