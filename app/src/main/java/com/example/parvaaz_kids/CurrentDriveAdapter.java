package com.example.parvaaz_kids;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;

import java.util.ArrayList;
import java.util.List;

public class CurrentDriveAdapter extends ArrayAdapter<Drives> {

    ArrayList<Drives> currentDrives;

    public CurrentDriveAdapter(@NonNull Context context, int resource, @NonNull ArrayList<Drives> objects) {
        super(context, resource, objects);
        this.currentDrives = objects;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        LayoutInflater layoutInflater = LayoutInflater.from(getContext());
        convertView = layoutInflater.inflate(R.layout.current_drive_view,parent, false);

        Drives drive = currentDrives.get(position);

        //Title
        TextView txt_current_drive_title = convertView.findViewById(R.id.current_drive_title);
        txt_current_drive_title.setText(drive.getTitle());

        //Contact
        TextView txt_currentDriveContact = convertView.findViewById(R.id.currentDriveContact);
        txt_currentDriveContact.setText(drive.getContact());

        //Target Amount
        TextView currentDriveTarget = convertView.findViewById(R.id.current_drive_target);
        currentDriveTarget.setText("Target: \n Rs"+drive.getTarget()+"");

        float percentage =  drive.getCollectedAmount() * 100 / drive.getTarget();

        //Progress Bar
        ProgressBar progressBar = convertView.findViewById(R.id.progressBar);
        progressBar.setProgress((int) percentage);

        //Progress Percentage
        TextView progressPercentage = convertView.findViewById(R.id.progressPercentage);
        progressPercentage.setText(percentage+"%");

        Button donate = convertView.findViewById(R.id.donateButttonList);



        //Donate here
        donate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(parent.getContext(),PaymentActivity.class);
                intent.putExtra("id",drive.getId());
                intent.putExtra("collectedAmount",drive.getCollectedAmount());
               // parent.getContext()
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                parent.getContext().startActivity(intent);
                //Toast.makeText(getContext(), "Donate Clicked", Toast.LENGTH_SHORT).show();
            }
        });

        return convertView;

    }




    private void showForgotDialog(Context c) {
        final EditText taskEditText = new EditText(c);
        AlertDialog dialog = new AlertDialog.Builder(c)
                .setTitle("Forgot Password")
                .setMessage("Enter your mobile number?")
                .setView(taskEditText)
                .setPositiveButton("Reset", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        String task = String.valueOf(taskEditText.getText());
                    }
                })
                .setNegativeButton("Cancel", null)
                .create();
        dialog.show();
    }

}

