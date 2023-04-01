package com.stopwatcholv.timer.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;

import com.stopwatcholv.timer.R;

public class ButtonsFragment extends Fragment {


    private static final String TAG = "ButtonsFragment";
    private ImageButton addBtn;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_buttons, container, false);
        addBtn = view.findViewById(R.id.fragment_add_btn);

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "onClick: open dialog to add note");
                FragmentDialogAddNote dialog = new FragmentDialogAddNote();
                //dialog.setTargetFragment(ButtonsFragment.this, 1);
                dialog.show(getActivity().getFragmentManager(), "FragmentDialogAddNote");
            }
        });
        return view;
    }

}