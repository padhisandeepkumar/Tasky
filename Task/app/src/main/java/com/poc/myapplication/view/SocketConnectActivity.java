package com.poc.myapplication.view;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.poc.myapplication.R;
import com.poc.myapplication.constants.Macros;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

public class SocketConnectActivity extends AppCompatActivity implements View.OnClickListener {
    TextView etIP, etPort;
    TextView tvMessages;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_socetconnection);
        etIP = findViewById(R.id.etIP);
        etPort = findViewById(R.id.etPort);
        tvMessages = findViewById(R.id.tvMessages);

        etIP.setText(Macros.IP);
        etPort.setText(String.format("%s", Macros.Port));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnConnect:
                ConnectivityManager connectivityManager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
                NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
                if (networkInfo != null && networkInfo.isConnected()
                        && networkInfo.isConnectedOrConnecting()
                        && networkInfo.isAvailable()) {
                    connect();
                } else {
                    tvMessages.setText(getString(R.string.error_net));
                }
                break;
        }
    }

    private void connect() {
        Thread thread = new Thread(new Runnable() {
            public void run() {
                Socket socket = null;
                DataInputStream dataInputStream = null;
                DataOutputStream dataOutputStream = null;
                try {
                    // Create a new Socket instance and connect to host
                    socket = new Socket(Macros.IP, Macros.Port);
                    dataOutputStream = new DataOutputStream(socket.getOutputStream());
                    dataInputStream = new DataInputStream(socket.getInputStream());

                    if (socket.isConnected()) {
                        setTextToMessage(getString(R.string.connected_msg));
                    } else {
                        setTextToMessage(getString(R.string.error_connection));
                    }

                    // transfer String Message to the server
                    dataOutputStream.writeUTF(Macros.message);

                    // Thread will wait till server replies
                    final String response = dataInputStream.readUTF();
                    if (!TextUtils.isEmpty(response)) {
                        setTextToMessage(response);
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                    setTextToMessage(TextUtils.isEmpty(e.getMessage()) ? getString(R.string.error_msg) : e.getMessage());
                } finally {
                    // close socket
                    if (socket != null) {
                        try {
                            socket.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            setTextToMessage(TextUtils.isEmpty(e.getMessage()) ? getString(R.string.error_msg) : e.getMessage());
                        }
                    }

                    // close input stream
                    if (dataInputStream != null) {
                        try {
                            dataInputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            setTextToMessage(TextUtils.isEmpty(e.getMessage()) ? getString(R.string.error_msg) : e.getMessage());
                        }
                    }

                    // close output stream
                    if (dataOutputStream != null) {
                        try {
                            dataOutputStream.close();
                        } catch (IOException e) {
                            e.printStackTrace();
                            setTextToMessage(TextUtils.isEmpty(e.getMessage()) ? getString(R.string.error_msg) : e.getMessage());
                        }
                    }
                }
            }
        });
        thread.start();

    }

    private void setTextToMessage(final String message) {
        runOnUiThread(new Runnable() {
            @Override
            public void run() {
                tvMessages.setText(message);
            }
        });
    }

}