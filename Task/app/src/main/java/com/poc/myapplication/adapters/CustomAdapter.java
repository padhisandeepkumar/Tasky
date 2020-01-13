package com.poc.myapplication.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.poc.myapplication.interfaces.CallBack;
import com.poc.myapplication.R;

public class CustomAdapter extends BaseAdapter {

    private Context context;
    private int cards[];
    private LayoutInflater inflter;
    private CallBack objListnier;

    public CustomAdapter(Context applicationContext, int[] pCards, CallBack pListnier) {
        this.context = applicationContext;
        this.cards = pCards;
        this.objListnier = pListnier;
        inflter = (LayoutInflater.from(applicationContext));
    }

    @Override
    public int getCount() {
        return cards.length;
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
        icon.setImageResource(cards[i]); // set logo images

        icon.setOnClickListener(v ->
                objListnier.OnItemCallBack(i)
        );
        return view;
    }
}
