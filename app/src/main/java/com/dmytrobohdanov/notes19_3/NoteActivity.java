package com.dmytrobohdanov.notes19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);
        NoteFragment noteFragment =
                (NoteFragment) getFragmentManager().findFragmentById(R.id.note_frag);
        int noteId = (int) getIntent().getExtras().get(EXTRA_NOTE_ID);
        noteFragment.setNoteId(noteId);
    }
}
