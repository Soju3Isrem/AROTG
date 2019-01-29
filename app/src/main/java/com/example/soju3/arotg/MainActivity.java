package com.example.soju3.arotg;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.soju3.arotg.ResponseStatus;
import com.example.soju3.arotg.USBSerialConnector;
import com.example.soju3.arotg.USBSerialListener;
import com.example.soju3.arotg.Utilities;

public class MainActivity extends AppCompatActivity implements USBSerialListener{

    USBSerialConnector mConnector;
    Button send;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        send = (Button)findViewById(R.id.send);
        mConnector = USBSerialConnector.getInstance();

        send.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mConnector.writeAsync("HOla".getBytes());
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        mConnector.setUsbSerialListener(this);
        mConnector.init(this,9600);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mConnector.pausedActivity();
    }

    @Override
    public void onDataReceived(byte[] data) {
        Toast.makeText(this,Utilities.bytesToHex(data),Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onErrorReceived(String data) {
        Toast.makeText(this,"Error al recivir",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeviceReady(ResponseStatus responseStatus) {
        Toast.makeText(this,"Error",Toast.LENGTH_SHORT).show();

    }

    @Override
    public void onDeviceDisconnected() {
        Toast.makeText(this,"Se a desconectadoj",Toast.LENGTH_SHORT).show();

    }
}
