package com.example.lab6_1.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.DialogFragment;

public class CommentDialog extends DialogFragment {

    public static CommentDialog newInstance() {
        return new CommentDialog();
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        builder.setTitle("Comment")
                .setMessage("This is the Comment dialog.")
                .setPositiveButton("OK", null);
        return builder.create();
    }
}
