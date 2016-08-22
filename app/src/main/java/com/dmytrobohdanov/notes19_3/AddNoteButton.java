package com.dmytrobohdanov.notes19_3;

import android.app.Activity;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.view.GravityCompat;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import java.lang.reflect.GenericArrayType;

public class AddNoteButton {
    private static final int LAYOUT_ID = R.id.main_layout;
    Activity activity;
    FloatingActionButton fab;

    public AddNoteButton(Activity activity) {
        this.activity = activity;

        fab = new FloatingActionButton(activity);
        setParamsToFAB(fab);
        setListener(fab);

        LinearLayout layout = (LinearLayout) activity.findViewById(LAYOUT_ID);
        layout.addView(fab);
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
//        return false;
    }

    /**
     * Setting params to Floating action button
     */
    private boolean setParamsToFAB(FloatingActionButton fab) {
        fab.setImageResource(R.mipmap.ic_add_note_button);
        fab.setSize(FloatingActionButton.SIZE_AUTO);

        //setting gravity
        CoordinatorLayout.LayoutParams params = new CoordinatorLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
        params.anchorGravity = Gravity.BOTTOM | GravityCompat.END;
        fab.setLayoutParams(params);
        return true;
    }
}
