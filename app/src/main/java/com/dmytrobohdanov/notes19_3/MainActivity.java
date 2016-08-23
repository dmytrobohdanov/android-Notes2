package com.dmytrobohdanov.notes19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class MainActivity extends AppCompatActivity implements NotesListFragment.NoteListListener {
    private OpenNoteHandler openNoteHandler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //initialization of addNoteButton
        AddNoteButton addNoteButton = new AddNoteButton(this);
    }

    @Override
    public void itemClicked(int id) {
        new OpenNoteHandler(this).openNote(id);
    }
}
