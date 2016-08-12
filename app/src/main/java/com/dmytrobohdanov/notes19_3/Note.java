package com.dmytrobohdanov.notes19_3;

import android.app.Activity;
import android.graphics.Color;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Date;
import java.util.Random;


/**
 * Note is class for keeping notes
 * each note has either id, color, text
 * or id, photo
 */

public class Note {
    //keeps last added note's id
    private static int idGenerator = 0;

    public static final int TITLE_MAX_LENGTH = 30;

    //array of note's colors
    //todo change colors (example - grey)
    private final int[] colors = {Color.rgb(64, 249, 138), Color.rgb(255, 255, 102)};

    //Note's data
    private int id;
    private int colorOfNote;
    private String titleOfNote;
    private String textOfNote;

    private String[] tags;
    private int[] colorTags;
    private long lastUpdateTime;
    private long createdTime;


    //Constructors

    /**
     * Constructor
     * for note without specified title
     *
     * @param text of note
     */
    public Note(String text) {
        String title = "";
        int textLength = text.length();
        if (textLength > Note.TITLE_MAX_LENGTH) {
            title = title + text.substring(0, 29) + "...";
        } else {
            title = title + text;
        }

        new Note(title, text);
    }

    /**
     * Constructor
     * for notes with specified title
     *
     * @param titleOfNote note's title
     * @param text        of note
     */
    public Note(String titleOfNote, String text) {
        setID();
        setBackgroundColorOfNote();
        setText(text);
        //todo: check length of title
        setTitle(titleOfNote);

        //set time of creation and last update
        long currentTime = getCurrentTime();
        setCreationTime(currentTime);
        setLastUpdateTime(currentTime);
    }


    /**
     * Constructor
     * for construct only from DB
     */
    public Note(int id, int colorOfNote, String titleOfNote, String textOfNote,
                String[] tags, int[] colorTags, long lastUpdateTime, long createdTime) {
        this.id = id;
        this.colorOfNote = colorOfNote;
        this.titleOfNote = titleOfNote;
        this.textOfNote = textOfNote;
        this.tags = tags;
        this.colorTags = colorTags;
        this.lastUpdateTime = lastUpdateTime;
        this.createdTime = createdTime;
    }


    //static methods

    /**
     * Translate milliseconds to user-readable format
     *
     * @param time in milliseconds
     * @return time string to display to user
     */
    public static String timeToString(long time) {
        String timeString;
        //todo get string from time. It should looks like: "Dec, 12" or "June, 15"
        return timeString;
    }


    //public methods


    //public setters

    /**
     * Sets title of note
     * and updates last update time
     *
     * @param title of note
     */
    public void setTitle(String title) {
        this.titleOfNote = title;
        setLastUpdateTime();
    }

    /**
     * Sets text of note
     * and updates last update time
     *
     * @param text of note
     */
    public void setText(String text) {
        this.textOfNote = text;
        setLastUpdateTime();
    }

    /**
     * Sets color of note
     * Needs for manual (outside of constructor) changing of color of note
     *
     * @param color color we want to set for note
     */
    public void setColor(int color) {
        this.colorOfNote = color;
    }


    //public getters

    /**
     * getID()
     *
     * @return ID of note
     */
    public int getID() {
        return id;
    }

    /**
     * getText()
     *
     * @return text of the note
     */
    public String getText() {
        return textOfNote;
    }

    /**
     * getTitle()
     *
     * @return title of the note
     */
    public String getTitle() {
        return titleOfNote;
    }

    /**
     * getColor()
     *
     * @return color of the note
     */
    public int getColor() {
        return colorOfNote;
    }


    /**
     * Set idGenerator - the variable thats sets our id initialization
     *
     * @param lastId - the id of the last note in our list
     */
    //todo here could be problem: should find out how works id adding after app reboot in case of DB
    public static void setIdGenerator(int lastId) {
        idGenerator = lastId;
    }


    //private methods

    /**
     * sets random background color (from array) for note
     */
    private void setBackgroundColorOfNote() {
        Random rnd = new Random();
//        getting random color from array of colors
        colorOfNote = colors[Math.abs(rnd.nextInt()) % colors.length];
    }

    /**
     * setting id of note
     */
    private void setID() {
        id = 1 + idGenerator++;
    }

    /**
     * Sets current time as time of last update of note
     */
    private void setLastUpdateTime() {
        //todo: rename it
//        this.lastUpdateTime = lastUpdateTime;
        this.lastUpdateTime = getCurrentTime();
    }

    /**
     * @return current time
     */
    private long getCurrentTime() {
        return new Date().getTime();
    }

    /**
     * Sets time if creation of the note
     *
     * @param creationTime time in milliseconds from 0-time
     */
    private void setCreationTime(long creationTime) {
        this.createdTime = creationTime;
    }
}

