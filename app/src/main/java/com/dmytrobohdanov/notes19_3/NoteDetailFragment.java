package com.dmytrobohdanov.notes19_3;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ViewSwitcher;

import java.util.ArrayList;

/**
 * Fragment which visualizes data from Note
 */
public class NoteDetailFragment extends Fragment {
    //string key to work with Bundle savedInstanceState
    private static final String NOTE_ID_KEY = "noteId";

    //id of note to display
    private int noteId;

    public NoteDetailFragment() {
    }

    /**
     * Specifing which note need to be shown in fragment
     *
     * @param indexInArray of element in list fragment
     */
    public void setNoteId(int indexInArray) {
        ArrayList<Note> notes = NotesKeeper.getInstance().getArrayOfNotes();
        Note note = notes.get(indexInArray);
        this.noteId = note.getID();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //if used have already selected note - display it
        //else - show empty fragment
        if (savedInstanceState != null) {
            noteId = savedInstanceState.getInt(NOTE_ID_KEY);
        }

        return inflater.inflate(R.layout.fragment_note, container, false);
    }

    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        savedInstanceState.putInt(NOTE_ID_KEY, noteId);
    }

    @Override
    public void onStart() {
        super.onStart();

        View view = getView();
        if (view != null) {
            //getting note with specified ID
            NotesKeeper notesKeeper = NotesKeeper.getInstance();
            Note note = notesKeeper.getNoteByID(noteId);

            //setting listeners
            ViewSwitcher vsTitle = (ViewSwitcher) view.findViewById(R.id.titleOfNote);
            Listeners.setTouchListener(vsTitle, this.getActivity(), Listeners.CHANGE_TO_EDITTEXT);
            Listeners.setOnFocusChangeListener(
                    view.findViewById(R.id.titleOfNote_edit),
                    noteId,
                    this.getActivity());


            ViewSwitcher vsText = (ViewSwitcher) view.findViewById(R.id.textOfNote);
            Listeners.setTouchListener(vsText, this.getActivity(), Listeners.CHANGE_TO_EDITTEXT);

            EditText editText = (EditText) view.findViewById(R.id.textOfNote_edit);
            editText.setOnFocusChangeListener(
            Listeners.setOnFocusChangeListener(
                    view.findViewById(R.id.textOfNote_edit),
                    noteId,
                    this.getActivity()));


            //displaying note in fragment
            TextView title = (TextView) view.findViewById(R.id.titleOfNote_text);
            title.setText(note.getTitle());

            TextView text = (TextView) view.findViewById(R.id.textOfNote_text);
//            title.setText(note.getText());
            //todo: temp - remove later
            String str = note.getText();
            if (str == null || str.isEmpty()) {
                str = str + "it was new note" + note.getID();
            }
            text.setText(str);


            TextView tags = (TextView) view.findViewById(R.id.tags);
            tags.setText(note.getTagsString());

            //todo: temp method until change textVIew to color-holder
            TextView color = (TextView) view.findViewById(R.id.createdTime);
            color.setText("temp text: here will be color-holder");

            TextView lastUpd = (TextView) view.findViewById(R.id.lastUpdate);
            lastUpd.setText(Note.timeToString(note.getLastUpdateTime()));

            TextView createdDate = (TextView) view.findViewById(R.id.createdTime);
            createdDate.setText(Note.timeToString(note.getCreationTime()));
        }

        //todo: if text field is empty - set focus to editText of textOfNote element
    }
}
