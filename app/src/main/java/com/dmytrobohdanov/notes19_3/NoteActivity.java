package com.dmytrobohdanov.notes19_3;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

public class NoteActivity extends AppCompatActivity {
    public static final String EXTRA_NOTE_ID = "id";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note);

        int noteId;
        //check which resource is launching activity
        //if it is launched by clicking on the note to show details, Extras has an id of...
        //...launching note and forming this activity with info from note
        // Otherwise Extras have no id and activity was launched for creation of new note
        //in code below we setting id of note launched for details in first case
        // and id of new note in case of creation of new note
        if (getIntent().getExtras().containsKey(EXTRA_NOTE_ID)) {
            //get note's id from Extras
            noteId = (int) getIntent().getExtras().get(EXTRA_NOTE_ID);
        } else {
            //creating new empty note
            Note note = new Note("");

            //adding new note to NoteKeeper
            NotesKeeper notesKeeper = NotesKeeper.getInstance();
            notesKeeper.addNote(note);

            //sets id of this note to variable noteId
            noteId = note.getID();
        }

        // creating note detail fragment with note's id
        NoteDetailFragment noteDetailFragment =
                (NoteDetailFragment) getFragmentManager().findFragmentById(R.id.note_frag);
        noteDetailFragment.setNoteId(noteId);
    }
}
