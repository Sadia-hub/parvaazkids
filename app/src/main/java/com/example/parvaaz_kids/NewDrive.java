package com.example.parvaaz_kids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;

public class NewDrive extends AppCompatActivity {

    private EditText driveName;
    private EditText contact;
    private EditText target;
    private EditText address;
    private EditText driveDesc;

    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_drive);

        db = FirebaseFirestore.getInstance();

        //Get References of form fields
        driveName = findViewById(R.id.driveName);
        contact = findViewById(R.id.contact);
        target = findViewById(R.id.target);
        address = findViewById(R.id.address);
        driveDesc = findViewById(R.id.driveDesc);

    }

    //For canceling new drive reistration
    public void cancel(View v){
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    //For clearing form data
    public void clear(View v){

       clearData();

    }

    //For adding new drive
    public void addDrive(View v){

        //Get drive names
        String driveName = this.driveName.getText().toString();
        String contact = this.contact.getText().toString();
        int target = Integer.parseInt(this.target.getText().toString());
        String address = this.address.getText().toString();
        String driveDesc = this.driveDesc.getText().toString();

        Drives drives = new Drives(driveName, driveDesc, contact, address, target);



        db.collection("drives")
                .add(drives)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Log.d("avc", "DocumentSnapshot added with ID: " + documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("avc", "Error adding document", e);
                    }
                });






        Toast.makeText(this, "Drive Added", Toast.LENGTH_SHORT).show();


        //clearData after inserting
        clearData();


    }

    private void clearData(){
        //Set text to empty striny
        driveName.setText("");
        contact.setText("");
        target.setText("");
        address.setText("");
        driveDesc.setText("");
    }

    public void back(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}