package com.stopwatcholv.timer.notes;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import android.content.Intent;
import android.os.Bundle;

import com.stopwatcholv.timer.R;

public class NotesActivity extends AppCompatActivity implements FragmentDialogAddNote.OnInputSelected{
    private FragmentTransaction ft;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes);

        ft = getSupportFragmentManager().beginTransaction();
        ft.add(R.id.linear_layout_notes, new NotesFragment("sdfddddd"));
        ft.add(R.id.linear_layout_notes, new NotesFragment("seconf"));
        ft.commit();

    }

    @Override
    public void sendInput(String input) {
        ft.add(R.id.linear_layout_notes, new NotesFragment(input));
        ft.commit();
    }
}