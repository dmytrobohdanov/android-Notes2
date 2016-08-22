package com.dmytrobohdanov.notes19_3;


import android.util.Log;

import java.util.ArrayList;

public class NotesKeeper {
    private final String LOG_TAG = this.getClass().getSimpleName();

    private static NotesKeeper instance;

    private ArrayList <Note> notes;


    //constructor
    private NotesKeeper(){
        notes = new ArrayList<>();

        //todo: remove temp lines
        addNote(new Note("First note", "text of it"));
        addNote(new Note("Second note", "text of it 2"));
        addNote(new Note("3rd note", "text of it 3"));
        addNote(new Note("4th note", "text of it 4"));
    }

    public static NotesKeeper getInstance(){
        if (instance == null) {
            instance = new NotesKeeper();
        }
        return instance;
    }


    /**
     * @return array of notes
     */
    public ArrayList<Note> getArrayOfNotes(){
        return notes;
    }

    /**
     * Adding note to array
     */
    public boolean addNote(Note note){
        return notes.add(note);
    }

    /**
     * Remove note with specified id
     *
     * @return true if succeed
     */
    public boolean removeNote(int noteId){
        boolean success = false;
        for (Note note: notes) {
            if (note.getID() == noteId){
                success = notes.remove(note);
                break;
            }
        }
        return success;
    }

    /**
     * Returns note with specified ID
     *
     * @param noteID id of note
     * @return note with specified ID,
     * or NULL in case if there is no note with such id
     */
    public Note getNoteByID(int noteID){
        for (Note note: notes) {
            if (noteID == note.getID()){
                return note;
            }
        }

        Log.e(LOG_TAG, "There is no note with such id");
        return null;
    }

    public int getIndexOf(Note note) {
        return notes.indexOf(note);
    }
}
