package com.example.ex2015_07_26;

import android.app.Activity;
import android.app.DialogFragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.ProgressBar;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.ex2015_07_26.activitys.LoginActivity;
import com.example.ex2015_07_26.core.Calculator;
import com.example.ex2015_07_26.core.CalculatorImpl;
import com.example.ex2015_07_26.fragments.dialogs.ExitDialogFragment;
import com.example.ex2015_07_26.fragments.dialogs.SimpleDialogFragment;
import com.example.ex2015_07_26.listeners.ButtonsListener;
import com.example.ex2015_07_26.listeners.ChangeTextDisplayListener;
import com.example.ex2015_07_26.listeners.ProgressBarListener;
import com.example.ex2015_07_26.listeners.ProgressBarTask;
import com.example.ex2015_07_26.listeners.RadioButtonListener;

import java.util.Date;


public class MyActivity
        extends Activity
        implements View.OnClickListener,
        SimpleDialogFragment.SaveDialogListener,
        ExitDialogFragment.ExitDialogListener {


    private static final String DISPLAY_SAVED_TEXT = "_a_";
    private static final long EXIT_TIME = 3 * 1000;
    private static final String TAG_SAVE_DIALOG = "saveDialog";
    private static final String TAG_EXIT_DIALOG = "exitDialog";

    private String mSavedText;
    private Date mBackButtonPressedTime;
    private Date timeOut;
    private Toast mExitToast;

    //region controls
    private TextView display;
    private ProgressBar progressBar;
    private final StringBuffer expression = new StringBuffer();
    Calculator calculator;
    View.OnClickListener buttonsListener;
    CompoundButton.OnCheckedChangeListener radioButtonsListener;
    ChangeTextDisplayListener changeTextDisplayListener;
    ProgressBarListener progressBarListener;
    private long sessionTime = 10 * 1000;
    //endregion


    //region lifecycle methods
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        display = (TextView) findViewById(R.id.display);
        if (display == null) Log.e("LifeCycle", "display not find !!!");
        progressBar = (ProgressBar) findViewById(R.id.progressBar);
        if (progressBar == null) Log.e("LifeCycle", "progress bar not find !!!");

        progressBarListener = new ProgressBarListener() {
            @Override
            public void runProgress() {
                ProgressBarTask task = new ProgressBarTask();
                task.setProgressBar(progressBar);
                task.execute();
            }
        };

        changeTextDisplayListener = new ChangeTextDisplayListener() {
            @Override
            public void changeTextDisplay(String newTextDisplay) {
                display.setText(newTextDisplay);
            }
        };

        calculator = new CalculatorImpl();
        buttonsListener = new ButtonsListener(calculator, expression, changeTextDisplayListener, progressBarListener);
        radioButtonsListener = new RadioButtonListener(expression, changeTextDisplayListener, progressBarListener);
        setListenersButton(buttonsListener);
        setListenerRadioButtons(radioButtonsListener);

        Log.wtf("LifeCycle", "OnCreate");
    }

    public void setListenerRadioButtons(CompoundButton.OnCheckedChangeListener radioButtonListener) {
        ((RadioButton) findViewById(R.id.radio_bin)).setOnCheckedChangeListener(radioButtonListener);
        ((RadioButton) findViewById(R.id.radio_oct)).setOnCheckedChangeListener(radioButtonListener);
        ((RadioButton) findViewById(R.id.radio_dec)).setOnCheckedChangeListener(radioButtonListener);
    }

    private void setListenersButton(View.OnClickListener buttonsListener) {
        Log.wtf("LifeCycle", "setListenersButton");
        findViewById(R.id.btn0).setOnClickListener(buttonsListener);
        findViewById(R.id.btn1).setOnClickListener(buttonsListener);
        findViewById(R.id.btn2).setOnClickListener(buttonsListener);
        findViewById(R.id.btn3).setOnClickListener(buttonsListener);
        findViewById(R.id.btn4).setOnClickListener(buttonsListener);
        findViewById(R.id.btn5).setOnClickListener(buttonsListener);
        findViewById(R.id.btn6).setOnClickListener(buttonsListener);
        findViewById(R.id.btn7).setOnClickListener(buttonsListener);
        findViewById(R.id.btn8).setOnClickListener(buttonsListener);
        findViewById(R.id.btn9).setOnClickListener(buttonsListener);
        findViewById(R.id.btnPlus).setOnClickListener(buttonsListener);
        findViewById(R.id.btnMin).setOnClickListener(buttonsListener);
        findViewById(R.id.btnDiv).setOnClickListener(buttonsListener);
        findViewById(R.id.btnMult).setOnClickListener(buttonsListener);
        findViewById(R.id.btnResult).setOnClickListener(buttonsListener);
        findViewById(R.id.btnComa).setOnClickListener(buttonsListener);
        findViewById(R.id.btnC).setOnClickListener(buttonsListener);
        findViewById(R.id.clearOneCharacter).setOnClickListener(buttonsListener);
        findViewById(R.id.btnPlazOrMin).setOnClickListener(buttonsListener);
        findViewById(R.id.btnSqrt).setOnClickListener(buttonsListener);

        if (findViewById(R.id.btnOpenBreck) != null) {
            findViewById(R.id.btnOpenBreck).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnCloseBreck) != null) {
            findViewById(R.id.btnCloseBreck).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnsin) != null) {
            findViewById(R.id.btnsin).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnExp) != null) {
            findViewById(R.id.btnExp).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnPi) != null) {
            findViewById(R.id.btnPi).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnLog) != null) {
            findViewById(R.id.btnLog).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnProcent) != null) {
            findViewById(R.id.btnProcent).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnTan) != null) {
            findViewById(R.id.btnTan).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnLn) != null) {
            findViewById(R.id.btnLn).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btn1divX) != null) {
            findViewById(R.id.btn1divX).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btncos) != null) {
            findViewById(R.id.btncos).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnXY) != null) {
            findViewById(R.id.btnXY).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnnoX) != null) {
            findViewById(R.id.btnnoX).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnsin) != null) {
            findViewById(R.id.btnsin).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.btnx2) != null) {
            findViewById(R.id.btnx2).setOnClickListener(buttonsListener);
        }
        if (findViewById(R.id.x13) != null) {
            findViewById(R.id.x13).setOnClickListener(buttonsListener);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.wtf("Lifecycle", "OnPause");
        timeOut = new Date();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Log.wtf("Lifecycle", "OnSaveInstanceState");
        outState.putString(DISPLAY_SAVED_TEXT, String.valueOf(display.getText()));
    }

    @Override
    protected void onPostResume() {
        super.onPostResume();
        Log.wtf("Lifecycle", "OnPostResume");

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.wtf("Lifecycle", "OnDestroy");
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.wtf("Lifecycle", "OnResume");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.wtf("Lifecycle", "OnRestart");
        Log.wtf("Lifecycle", "session is " + sessionIsCorrect());
        if (sessionIsCorrect()) {
            startActivity(new Intent(this, LoginActivity.class));
            finish();
        }
    }

    boolean sessionIsCorrect() {
        if(timeOut == null) timeOut = new Date();
        return (timeOut.getTime() + sessionTime) < new Date().getTime();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.wtf("Lifecycle", "OnStart");

    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.wtf("Lifecycle", "OnRestoreInstanceState");
        display.setText(savedInstanceState.getString(DISPLAY_SAVED_TEXT));
        expression.append(display.getText());
    }

    //endregion

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.saveButton:
                saveButtonHandler();
                break;
            case R.id.restoreButton:
                restoreButtonHandler();
                break;
        }
    }

    private void restoreButtonHandler() {
        display.setText(mSavedText);
    }

    private void saveButtonHandler() {
        if (nothingSavedYet()) {
            saveTextFromDisplay();
        } else {
            showSaveDialog();
        }
    }

    private boolean nothingSavedYet() {
        return mSavedText == null;
    }


    private void saveTextFromDisplay() {
        mSavedText = display.getText().toString();
        showSavedToast();
    }

    private void showSaveDialog() {
        DialogFragment saveDialog = new SimpleDialogFragment(this);
        saveDialog.show(getFragmentManager(), TAG_SAVE_DIALOG);
    }

    @Override
    public void onBackPressed() {
//        if (checkExitCheckbox.isChecked()){
//            exitDialog();
//        } else {
        exitToast();
//        }
    }

    private void exitDialog() {
        DialogFragment exitDialogFragment = new ExitDialogFragment(this);
        exitDialogFragment.show(getFragmentManager(), TAG_EXIT_DIALOG);
    }

    private void exitToast() {
        if (mBackButtonPressedTime == null) {
            firstPressingOfBackButton();
        } else {
            secondPressingOfBackButton();
        }
    }

    private void firstPressingOfBackButton() {
        mBackButtonPressedTime = new Date();
        showExitToast();
    }

    //region toasts
    private void showSavedToast() {
        Toast.makeText(this, R.string.toast_saved, Toast.LENGTH_SHORT).show();
    }

    private void showExitToast() {
//        Toast.makeText(this, R.string.exit_toast_text,
//                Toast.LENGTH_LONG).show();

        View toastView = getLayoutInflater().inflate(R.layout.exit_toast_layout, null);
        mExitToast = new Toast(this);
        mExitToast.setDuration(Toast.LENGTH_LONG);
        mExitToast.setGravity(Gravity.BOTTOM, 0, 0);
        mExitToast.setView(toastView);
        mExitToast.show();
    }

    //endregion

    private void secondPressingOfBackButton() {
        if (requiredTimeElapsed()) {
            exitActivity();
        } else {
            firstPressingOfBackButton();
        }

    }

    private void exitActivity() {
        if (mExitToast != null) {
            mExitToast.cancel();
        }
        finish();
    }

    private boolean requiredTimeElapsed() {
        Date currentTime = new Date();
        return currentTime.getTime() - mBackButtonPressedTime.getTime() < EXIT_TIME;
    }

    @Override
    public void saveText() {
        saveTextFromDisplay();
    }

    @Override
    public void exit() {
        exitActivity();
    }
}
