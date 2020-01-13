package com.poc.myapplication.view;

import android.os.Bundle;
import android.view.View;
import android.widget.GridView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.poc.myapplication.interfaces.CallBack;
import com.poc.myapplication.R;
import com.poc.myapplication.adapters.CustomAdapter;
import com.poc.myapplication.adapters.SelectedAdapter;
import com.poc.myapplication.constants.Macros;

import org.apache.commons.lang3.ArrayUtils;

import java.util.ArrayList;

public class CardActivity extends AppCompatActivity implements View.OnClickListener, CallBack {

    private GridView totalCardGrid;
    private GridView selectedCardGrid;

    private CustomAdapter allAdapter;
    private SelectedAdapter selectedAdapter;
    private ArrayList<Integer> selectedValues;
    private int allCards[];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);

        init();
        loadAllCards();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_group:
                selectedValues.sort(Integer::compareTo);
                loadSelectedCards();
                break;
        }
    }

    @Override
    public void OnItemCallBack(int pPosition) {
        if (selectedValues.size() <= 12) {
            selectedValues.add(allCards[pPosition]);
            loadSelectedCards();

            allCards = ArrayUtils.remove(allCards, pPosition);
            loadAllCards();
        } else {
            Toast.makeText(this, getText(R.string.error_max), Toast.LENGTH_LONG).show();
        }
    }

    private void init() {
        selectedValues = new ArrayList<>();
        allCards = Macros.totalCards;
        totalCardGrid = findViewById(R.id.grid_cards);
        selectedCardGrid = findViewById(R.id.grid_selected);
    }

    private void loadAllCards() {
        allAdapter = new CustomAdapter(getApplicationContext(), allCards, this);
        totalCardGrid.setAdapter(allAdapter);
        allAdapter.notifyDataSetChanged();
    }

    private void loadSelectedCards() {
        selectedAdapter = new SelectedAdapter(getApplicationContext(), selectedValues);
        selectedCardGrid.setAdapter(selectedAdapter);
        selectedAdapter.notifyDataSetChanged();
    }

}