package com.dmytrobohdanov.notes19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

/**
 * Holds fragment with note's data in case of small(portrait) display
 */
public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID_KEY = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        int noteId;
        //get note's id from Extras
        noteId = (int) getIntent().getExtras().get(EXTRA_NOTE_ID_KEY);

        // creating note detail fragment with note's id
        NoteDetailFragment noteDetailFragment =
                (NoteDetailFragment) getFragmentManager().findFragmentById(R.id.note_frag);
        noteDetailFragment.setNoteId(noteId);
    }
}
