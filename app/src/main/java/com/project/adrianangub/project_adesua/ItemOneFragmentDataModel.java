package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 21/04/2018.
 */

public class ItemOneFragmentDataModel
{
    String text;
    String comment;
    String date;

    public ItemOneFragmentDataModel(String text, String comment, String date)
    {
        this.text = text;
        this.comment = comment;
        this.date = date;
    }

    public String getText()
    {
        return text;
    }

    public String getComment()
    {
        return comment;
    }

    public String getDate()
    {
        return date;
    }
}