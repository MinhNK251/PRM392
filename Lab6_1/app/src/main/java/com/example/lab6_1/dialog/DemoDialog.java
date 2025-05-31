package com.example.lab6_1.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class DemoDialog extends DialogFragment {

    public static DemoDialog newInstance() {
        return new DemoDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Demo")
                .setMessage("This is the Demo dialog.")
                .setPositiveButton("OK", null);
        return builder.create();
    }
}
