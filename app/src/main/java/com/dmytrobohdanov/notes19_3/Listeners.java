package com.dmytrobohdanov.notes19_3;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ViewSwitcher;

public class Listeners {
    //action types
    public static final String CHANGE_TO_EDITTEXT = "to edit text";



    public static View.OnFocusChangeListener setOnFocusChangeListener(View view, final int noteId, final Activity activity) {
        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View view, boolean hasFocus) {
                if (!view.hasFocus()) {
                    Toast.makeText(activity, "ping", Toast.LENGTH_LONG).show();
                    //getting note id from array
                    Note note = NotesKeeper.getInstance().getNoteByID(noteId);

                    // view - expecting Edit text
                    EditText editText = (EditText) view;

                    ViewSwitcher vs = null;
                    //todo: refactor this
                    if (view.getId() == R.id.titleOfNote_edit) {
                        Toast.makeText(activity, "title of note", Toast.LENGTH_LONG).show();

                        TextView textView = (TextView) view.findViewById(R.id.titleOfNote_text);
                        String newTitle = editText.getText().toString();
                        textView.setText(newTitle);
                        note.setTitle(newTitle);
                        vs = (ViewSwitcher) activity.findViewById(R.id.titleOfNote);
                    } else if (view.getId() == R.id.textOfNote_edit) {
                        Toast.makeText(activity, "text of note", Toast.LENGTH_LONG).show();
                        TextView textView = (TextView) view.findViewById(R.id.textOfNote_text);
                        String newText = editText.getText().toString();
                        textView.setText(newText);
                        note.setText(newText);
                        vs = (ViewSwitcher) activity.findViewById(R.id.textOfNote);
                    } else {
                        Toast.makeText(activity, "check focus listener", Toast.LENGTH_LONG).show();
                    }
                    assert vs != null;
                    vs.showPrevious();
                }
            }
        };
        return focusChangeListener;
//        view.setOnFocusChangeListener(focusChangeListener);
    }
//
//    public static void setOnFocusChangeListener(View view, final int noteId, final Activity activity) {
//        View.OnFocusChangeListener focusChangeListener = new View.OnFocusChangeListener() {
//            @Override
//            public void onFocusChange(View view, boolean hasFocus) {
//                if (!view.hasFocus()) {
//                    Toast.makeText(activity, "ping", Toast.LENGTH_LONG).show();
//                    //getting note id from array
//                    Note note = NotesKeeper.getInstance().getNoteByID(noteId);
//
//                    // view - expecting Edit text
//                    EditText editText = (EditText) view;
//
//                    ViewSwitcher vs = null;
//                    //todo: refactor this
//                    if (view.getId() == R.id.titleOfNote_edit) {
//                        Toast.makeText(activity, "title of note", Toast.LENGTH_LONG).show();
//
//                        TextView textView = (TextView) view.findViewById(R.id.titleOfNote_text);
//                        String newTitle = editText.getText().toString();
//                        textView.setText(newTitle);
//                        note.setTitle(newTitle);
//                        vs = (ViewSwitcher) activity.findViewById(R.id.titleOfNote);
//                    } else if (view.getId() == R.id.textOfNote_edit) {
//                        Toast.makeText(activity, "text of note", Toast.LENGTH_LONG).show();
//                        TextView textView = (TextView) view.findViewById(R.id.textOfNote_text);
//                        String newText = editText.getText().toString();
//                        textView.setText(newText);
//                        note.setText(newText);
//                        vs = (ViewSwitcher) activity.findViewById(R.id.textOfNote);
//                    } else {
//                        Toast.makeText(activity, "check focus listener", Toast.LENGTH_LONG).show();
//                    }
//                    assert vs != null;
//                    vs.showPrevious();
//                }
//            }
//        };
//        view.setOnFocusChangeListener(focusChangeListener);
//    }

    /**
     * sets onTouchListener to view depending on view type
     * imageView: onClick show full image
     * textView: onClick shows editText view
     * all views: deleting onSwipeRight
     *
     * @param view     we are working with
     * @param activity we are working with
     * @param flag     action type
     */
    public static void setTouchListener(final View view, final Activity activity, String flag) {
        if (flag.equals(CHANGE_TO_EDITTEXT)) {
            view.setOnTouchListener(new OnTouchListener(activity) {
                @Override
                public void onTapEvent() {
                    changeToEditText(activity, (ViewSwitcher) view);
                }
            });
        }
    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.HIDE_IMPLICIT_ONLY, 0);
    }

    /**
     * Changing TextView to EditText in ViewSwitcher
     * should contain
     */
    private static void changeToEditText(Activity activity, ViewSwitcher vs) {
        TextView textView = (TextView) vs.getCurrentView();
        EditText editText = (EditText) vs.getNextView();

        editText.setText(textView.getText());
        vs.showNext();
        showKeyboard(activity);
        editText.requestFocus();
        //todo: rewrite it: handle situation when vs.showNext() is null, i.e. vs is already shows EditText
    }

    /**
     * Shows keyboard
     */
    private static void showKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        imm.toggleSoftInput(InputMethodManager.SHOW_IMPLICIT, 0);
    }
}
