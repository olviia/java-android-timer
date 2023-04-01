package com.stopwatcholv.timer.notes;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

import android.app.DialogFragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import com.stopwatcholv.timer.R;

public class FragmentDialogAddNote extends DialogFragment {
    private static final String TAG = "FragmentDialogAddNote";

    public interface OnInputSelected{
        void sendInput (String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private EditText dInput;
    private TextView dActionOk, dActionCancel;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog_add_note, container, false);
        dInput = view.findViewById(R.id.input);
        dActionOk = view.findViewById(R.id.ok);
        dActionCancel = view.findViewById(R.id.cancel);

        dActionCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                getDialog().dismiss();
            }
        });
        dActionOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String input = dInput.getText().toString();
                if(!input.equals("")){
                    mOnInputSelected.sendInput(input);
                }
                getDialog().dismiss();
            }
        });
        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        } catch (ClassCastException e){
            Log.e(TAG,"onAttach exception : " + e.getMessage());
        }
    }
}