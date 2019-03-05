package com.doanhld.quiz.dialog;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;

import com.doanhld.quiz.R;

public class EnglishDialog  extends DialogFragment {
    Button btnOK;
    Button btnCancel;
    public interface EnglishDialogListener {
        void onPosClickListener();
        void onNeClickListener();
    }
    private EnglishDialogListener englishDialogListener;

    public void setEnglishDialogListener(EnglishDialogListener englishDialogListener) {
        this.englishDialogListener = englishDialogListener;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {

        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = getActivity().getLayoutInflater();
        View v = inflater.inflate(R.layout.english_dialog,null);
        btnOK = v.findViewById(R.id.btn_ok);
        btnCancel = v.findViewById(R.id.btn_cancel);

        btnOK.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                englishDialogListener.onPosClickListener();
            }
        });
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                englishDialogListener.onNeClickListener();
            }
        });
        builder.setView(v);

        return builder.create() ;
    }
}
