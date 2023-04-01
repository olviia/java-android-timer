package com.stopwatcholv.timer.notes;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.stopwatcholv.timer.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link NotesFragment#} factory method to
 * create an instance of this fragment.
 */
public class NotesFragment extends Fragment {

    private String noteText;
    private TextView noteView;


    public NotesFragment(String noteText) {
        this.noteText = noteText;
    }
    public NotesFragment() {
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_notes, container, false);
        noteView = v.findViewById(R.id.notes_text);
        noteView.setText(noteText);

        return v;
    }
}