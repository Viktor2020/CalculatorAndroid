package com.example.ex2015_07_26.fragments.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

@SuppressLint("ValidFragment")
public class ExitDialogFragment extends DialogFragment implements DialogInterface.OnClickListener{

    public interface ExitDialogListener {
        void exit();
    }

private ExitDialogListener mExitDialogListener;

    @SuppressLint("ValidFragment")
    public ExitDialogFragment(ExitDialogListener mExitDialogListener) {
        this.mExitDialogListener = mExitDialogListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Диалоговое окно наше")
                .setCancelable(false)
                .setMessage("Выйти?")
                .setPositiveButton("Да", this)
                .setNegativeButton("Нет", this);

        return alertBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which) {
            case DialogInterface.BUTTON_POSITIVE:
                mExitDialogListener.exit();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }
}
