package com.example.parvaaz_kids;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;

import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;


public class ConfirmationDialog extends DialogFragment {

    PaymentActivity context;

    private boolean confirm = false;

    public boolean getConfirm(){
        return confirm;
    }
    String msg;

    ConfirmationDialog(PaymentActivity context, String msg){
        this.msg = msg;
        this.context = context;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage("Do you want to donate "+msg)
                .setPositiveButton("Confirm", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // FIRE ZE MISSILES!
                        confirm = true;
                        context.donate();

                    }
                })
                .setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        // User cancelled the dialog
                        confirm = false;
                    }
                });
        // Create the AlertDialog object and return it
        return builder.create();
    }
}

