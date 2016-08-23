package com.dmytrobohdanov.notes19_3;


import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.view.View;

/**
 * Opening note class
 */
public class OpenNoteHandler {
//    private static OpenNoteHandler instance;
    private Activity activity;

    OpenNoteHandler(Activity activity) {
        this.activity = activity;
    }

//    public static OpenNoteHandler getInstance(Activity activity) {
//        if (instance == null) {
//            instance = new OpenNoteHandler(activity);
//        }
//        return instance;
//    }

    /**
     * Opening specified note in a fragment
     * fragment could be placed in same activity or in new one
     *
     * @param index of note in array of notes in NotesKeeper
     */
    public void openNote(int index) {
        View fragmentContainer = activity.findViewById(R.id.fragment_container);

        //there is fragment container only in case of layout-land,
        //so if it is not null - using it, else - using new activity
        if (fragmentContainer != null) {
            NoteDetailFragment noteDetailFragment = new NoteDetailFragment();
            FragmentTransaction ft = activity.getFragmentManager().beginTransaction();
            noteDetailFragment.setNoteId(index);
            ft.replace(R.id.fragment_container, noteDetailFragment);
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
            ft.commit();
        } else {
            Intent intent = new Intent(activity, NoteActivity.class);
            intent.putExtra(NoteActivity.EXTRA_NOTE_ID_KEY, index);
            activity.startActivity(intent);
        }
    }
}
