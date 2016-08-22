package com.dmytrobohdanov.notes19_3;

import android.graphics.Color;
import android.util.Log;
import android.widget.Toast;

import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Random;


/**
 * Note is class for keeping notes
 */
//todo: write description

public class Note {
    private final String LOG_TAG = this.getClass().getSimpleName();

    //keeps last added note's id
    private static int idGenerator = 0;

    public static final int TITLE_MAX_LENGTH = 30;

    //array of note's colors
    //todo change colors (example - shades of grey)
    private final int[] colors = {Color.rgb(64, 249, 138), Color.rgb(255, 255, 102)};

    //Note's data
    private int id;
    private int colorOfNote;
    private String titleOfNote;
    private String textOfNote;

    private HashSet<String> tags;
    private HashSet<Integer> colorTags;
    private long lastUpdateTime;
    private long createdTime;


    //Constructors

    /**
     * Constructor
     * for note without specified title
     * cuts first Note.TITLE_MAX_LENGTH symbols from text and sets them as title
     *
     * @param text of note
     */
    public Note(String text) {
        //creating note with temp title
        this("", text);

        //setting new correct title
        String title = "";
        int textLength = text.length();
        if (textLength > Note.TITLE_MAX_LENGTH) {
            title = title + text.substring(0, 29) + "...";
        } else {
            title = title + text;
        }

        setTitle(title);
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

        //initialization of tags and colorTags
        tags = new HashSet<>();
        colorTags = new HashSet<>();

        //set time of creation and last update
        setCreationTime(getCurrentTime());
        setLastUpdateTime();
    }


    /**
     * Constructor
     * for construct only from DB
     */
    public Note(int id, int colorOfNote, String titleOfNote, String textOfNote,
                String[] tags, Integer[] colorTags, long lastUpdateTime, long createdTime) {
        this.id = id;
        this.colorOfNote = colorOfNote;
        this.titleOfNote = titleOfNote;
        this.textOfNote = textOfNote;
        this.lastUpdateTime = lastUpdateTime;
        this.createdTime = createdTime;

        //adding colorTags to note
        //todo: could be not comfortable working with Integer instead of int
        Collections.addAll(this.colorTags, colorTags);
//        for(int color: colorTags){
//            this.colorTags.add(color);
//        }

        //adding tags to note
        Collections.addAll(this.tags, tags);
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
        //temp:
        timeString = "time: " + time;
        return timeString;
    }


    //public methods

    /**
     * Adding new tag to array of tags
     */
    public boolean addTag(String newTag) {
        return this.tags.add(newTag);
    }

    /**
     * Adding new color tag to note
     */
    public boolean addColorTag(int newColorTag) {
        return this.colorTags.add(newColorTag);
    }

    /**
     * Removing specified tag from note
     */
    public boolean removeTag(String tagToRemove) {
        return this.tags.remove(tagToRemove);
    }

    /**
     * Removing specified color tag from note
     */
    public boolean removeColorTag(int colorTagToRemove) {
        return this.colorTags.remove(colorTagToRemove);
    }


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
     * @return HashSet of tags of note
     */
    public HashSet<String> getTags() {
        return tags;
    }

    /**
     * Forming string from array of tags
     *
     * @return string of text
     */
    public String getTagsString() {
        String tagsString = "";

        if(tags == null || tags.isEmpty()){
            return tagsString;
        }

        //forming string from array of tags
        for (String tag : tags) {
            tagsString += ("#" + tag + " ");
        }

        //cutting last space
        tagsString = tagsString.substring(0, tagsString.length() - 1);

        return tagsString;
    }

    /**
     * @return HashSet of color tags of note
     */
    public HashSet<Integer> getColorTags() {
        return colorTags;
    }

    /**
     * @return cteation time in millisec from 0-time
     */
    public long getCreationTime() {
        return this.createdTime;
    }

    /**
     * @return last update time in millisec from 0-time
     */
    public long getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    /**
     * Returns title of note
     * <p/>
     * needed to form array for list fragment adapter
     * we pass array of notes to adapter and it use toString() method to for list of elements
     */ //todo rewrite
    public String toString() {
        return this.titleOfNote;
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