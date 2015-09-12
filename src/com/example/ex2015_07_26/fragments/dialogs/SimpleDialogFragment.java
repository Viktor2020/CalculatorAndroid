package com.example.ex2015_07_26.fragments.dialogs;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;

public class SimpleDialogFragment extends DialogFragment implements DialogInterface.OnClickListener {

    public interface SaveDialogListener {
        void saveText();
    }

    private SaveDialogListener mSaveDialogListener;

    public SimpleDialogFragment() { }

    @SuppressLint("ValidFragment")
    public SimpleDialogFragment(SaveDialogListener mSaveDialogListener) {
        this.mSaveDialogListener = mSaveDialogListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        AlertDialog.Builder alertBuilder = new AlertDialog.Builder(getActivity());
        alertBuilder.setTitle("Диалоговое окно наше")
                .setCancelable(false)
                .setMessage("Запись уже существует. Перезаписать?")
                .setPositiveButton("Да", this)
                .setNegativeButton("Нет", this);

        return alertBuilder.create();
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {
        switch(which) {
            case DialogInterface.BUTTON_POSITIVE:
                mSaveDialogListener.saveText();
                break;
            case DialogInterface.BUTTON_NEGATIVE:
                break;
        }
    }
}
