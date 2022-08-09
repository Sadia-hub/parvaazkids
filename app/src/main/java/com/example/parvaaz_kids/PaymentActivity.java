package com.example.parvaaz_kids;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.app.Dialog;
import android.content.DialogInterface;
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

public class PaymentActivity extends AppCompatActivity {

    private String id;
    float collectedAmount;
    FirebaseFirestore db;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_payment);
        id = getIntent().getStringExtra("id");
        collectedAmount = getIntent().getIntExtra("collectedAmount",0);



        db = FirebaseFirestore.getInstance();
       // Toast.makeText(this,id+collectedAmount+"", Toast.LENGTH_SHORT).show();


    }

    public void back(View v){
        Intent intent = new Intent(this, CurrentDrives.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }

    public void donate(View v){
        EditText donationAmount = findViewById(R.id.donationAmount);

        if(!donationAmount.getText().toString().isEmpty()){
            float donation = Integer.parseInt(donationAmount.getText().toString());
            this.collectedAmount+=donation;

            FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
            ConfirmationDialog confirmationDialog = new ConfirmationDialog(this,donation+"Rs to Parvaaaz kids?");

            confirmationDialog.show(ft, "drive");

        }

    }

    public void donate(){
        Intent intent = new Intent(this, MoneyTransfer.class);
        DocumentReference drive= db.collection("drives").document(this.id);
        //drive.get("collectedAmount");
        if(drive==null){return;}

        drive
                .update("collectedAmount", this.collectedAmount)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d("drive", "DocumentSnapshot successfully updated!");
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w("drive", "Error updating document", e);
                    }
                });
    }

    public void cancel(View v){
        Intent intent = new Intent(this, CurrentDrives.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}


