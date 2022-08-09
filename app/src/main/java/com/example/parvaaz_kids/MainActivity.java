package com.example.parvaaz_kids;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);





    }

    public void seeCurrentDrives(View v){
        finish();
        Intent intent = new Intent(this, CurrentDrives.class);
        startActivity(intent);
    }

    public void startNewDrive(View v){
        finish();
        Intent intent = new Intent(this, NewDrive.class);
        startActivity(intent);
    }
}