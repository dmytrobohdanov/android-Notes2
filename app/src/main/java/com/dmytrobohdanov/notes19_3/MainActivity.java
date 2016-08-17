package com.dmytrobohdanov.notes19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.app.FragmentTransaction;

public class MainActivity extends AppCompatActivity implements NotesListFragment.NoteListListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @Override
    public void itemClicked(int id) {
        View fragmentContainer = findViewById(R.id.fragment_container);

        //there is fragment container only in case of layout-land,
        //so if it is not null - using it, else - using new activity
        if (fragmentContainer != null) {
            NoteFragment noteFragment = new NoteFragment();
            FragmentTransaction ft = getFragmentManager().beginTransaction();
            noteFragment.setNoteId(id);
            ft.replace(R.id.fragment_container, noteFragment);
//            ft.addToBackStack(null);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            //todo
        }
    }
}
