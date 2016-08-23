package com.dmytrobohdanov.notes19_3;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;


public class MainActivity extends AppCompatActivity implements NotesListFragment.NoteListListener {
    private OpenNoteHandler openNoteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);// new AddNoteButton(this);
        setListener(fab);

//        openNoteHandler = OpenNoteHandler.getInstance(this);
    }

    @Override
    public void itemClicked(int id) {
//        openNoteHandler.openNote(id);
        new OpenNoteHandler(this).openNote(id);
    }
    private void setListener(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note("");
                NotesKeeper notesKeeper = NotesKeeper.getInstance();
                notesKeeper.addNote(note);
                NotesListFragment.notifyArrayChanges();
                new OpenNoteHandler(MainActivity.this).openNote(notesKeeper.getIndexOf(note));
            }
        });
//        return false;
    }

}
