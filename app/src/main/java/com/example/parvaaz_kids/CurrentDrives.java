package com.example.parvaaz_kids;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class CurrentDrives extends AppCompatActivity {

    private ArrayList<Drives> currentDrives;

    FirebaseFirestore db;
    ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_current_drives);
        listview = findViewById(R.id.current_drives_list);

        currentDrives = new ArrayList<>();

        db = FirebaseFirestore.getInstance();

        db.collection("drives")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Drives drive = document.toObject(Drives.class);
                                drive.setId(document.getId());
                                Log.d("drive", drive.getId() + " => " + document.getData());

                                if(drive.getCollectedAmount()<drive.getTarget()){
                                    currentDrives.add(drive);
                                }


                            }
                            ListView listview = findViewById(R.id.current_drives_list);
                            CurrentDriveAdapter adapter = new CurrentDriveAdapter(getApplicationContext(), R.layout.current_drive_view,currentDrives);
                            listview.setAdapter(adapter);
                        } else {
                            Log.w("drive", "Error getting documents.", task.getException());
                        }
                    }
                });

    }

    @Override
    protected void onStart() {
        super.onStart();
        db.collection("drives")
                .addSnapshotListener(new EventListener<QuerySnapshot>() {
                    @Override
                    public void onEvent(@Nullable QuerySnapshot value,
                                        @Nullable FirebaseFirestoreException e) {
                        if (e != null) {
                            Log.w("drive", "Listen failed.", e);
                            return;
                        }

                        ArrayList<Drives> updatedDrives  = new ArrayList<>();
                        for (QueryDocumentSnapshot document : value) {
                            Drives drive = document.toObject(Drives.class);
                            drive.setId(document.getId());
                            if(drive.getCollectedAmount()<drive.getTarget()){
                                updatedDrives.add(drive);
                            }
                        }

                        CurrentDriveAdapter adapter = new CurrentDriveAdapter(getApplicationContext(), R.layout.current_drive_view,updatedDrives);
                        listview.setAdapter(adapter);
                        Log.d("drive", "Current drives: " + updatedDrives);
                    }
                });
    }

    public void back(View v){
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}