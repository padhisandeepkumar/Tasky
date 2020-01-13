package com.poc.myapplication.view;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import com.poc.myapplication.R;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            // GAME TASK
            case R.id.btn_taskone:
                intent = new Intent(MainActivity.this, CardActivity.class);
                startActivity(intent);
                break;
            // SOCKET CONNECTION TASK
            case R.id.btn_tasktwo:
                intent = new Intent(MainActivity.this, SocketConnectActivity.class);
                startActivity(intent);
                break;
        }
    }
}