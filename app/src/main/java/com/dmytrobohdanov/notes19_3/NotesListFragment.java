package com.dmytrobohdanov.notes19_3;

import android.app.Activity;
import android.app.ListFragment;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class NotesListFragment extends ListFragment {
    interface NoteListListener {
        void itemClicked(int id);
    }

    private NoteListListener listener;
    private static ArrayAdapter<Note> adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        //associating list of notes with listFragment using ArrayAdapter
        adapter = new ArrayAdapter<>(
                inflater.getContext(),
                android.R.layout.simple_list_item_1,
                NotesKeeper.getInstance().getArrayOfNotes());

        setListAdapter(adapter);

        return super.onCreateView(inflater, container, savedInstanceState);
    }

//    @Override
//    public void onAttach(Context context) {
//        super.onAttach(context);
//        this.listener = (NoteListListener) context;
//    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        this.listener = (NoteListListener) activity;
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        if (listener != null) {
            listener.itemClicked((int) id);
        }
    }

    /**
     * notifing adapter about changing array
     */
    public static void notifyArrayChanges(){
        adapter.notifyDataSetChanged();
    }

}
