package com.dmytrobohdanov.notes19_3;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import java.lang.reflect.GenericArrayType;

/**
 * Initialization of floating action button for
 * adding new note
 */
public class AddNoteButton {
    private final int FAB_ID = R.id.fab;
    private Activity activity;
    private FloatingActionButton fab;

    public AddNoteButton(Activity activity) {
        this.activity = activity;

        fab = (FloatingActionButton) activity.findViewById(FAB_ID);
        setListener(fab);
    }

    /**
     * Set onClick listener to floating action button
     * onClick: adding new empty note, adding it to NotesKeeper, open fragment with this note
     */
    private void setListener(FloatingActionButton fab) {
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Note note = new Note("");
                NotesKeeper notesKeeper = NotesKeeper.getInstance();
                notesKeeper.addNote(note);
                NotesListFragment.notifyArrayChanges();
                new OpenNoteHandler(activity).openNote(notesKeeper.getIndexOf(note));
            }
        });
    }
}
