package com.example.parvaaz_kids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

public class MoneyTransfer extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_money_transfer);

        Handler handler = new Handler();        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                //Do something after delay
                finish();
                startActivity(new Intent(MoneyTransfer.this, Blessing.class));
            }
        }, 11000);

    }
}