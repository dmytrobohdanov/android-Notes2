package com.dmytrobohdanov.notes19_3;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;


public class MainActivity extends AppCompatActivity implements NotesListFragment.NoteListListener {
    private OpenNoteHandler openNoteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        AddNoteButton addNoteButton = new AddNoteButton(this);
//        openNoteHandler = OpenNoteHandler.getInstance(this);
    }

    @Override
    public void itemClicked(int id) {
//        openNoteHandler.openNote(id);
        new OpenNoteHandler(this).openNote(id);
    }

}
