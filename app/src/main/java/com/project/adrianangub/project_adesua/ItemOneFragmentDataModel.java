package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 21/04/2018.
 */

public class ItemOneFragmentDataModel
{
    private String t;
    private String cover;
    private String id;
    private String auth;
    private String callcrd;
    private String haspdf;
    private String totalbooks;
    private String pubname;
    private String pubdate;
    private String isbn;
    private String issn;
    private String lbs;

    public ItemOneFragmentDataModel(String t, String cover, String id, String auth, String callcrd, String haspdf, String totalbooks, String pubname, String pubdate, String isbn, String issn,  String lbs)
    {
        this.t = t;
        this.cover = cover;
        this.id = id;
        this.auth = auth;
        this.callcrd = callcrd;
        this.haspdf = haspdf;
        this.totalbooks = totalbooks;
        this.pubname = pubname;
        this.pubdate = pubdate;
        this.isbn = isbn;
        this.issn = issn;
        this.lbs = lbs;
    }

    public String getT() {
        return t;
    }
    public String getCover() {
        return cover;
    }
    public String getId() {
        return id;
    }
    public String getAuth() {
        return auth;
    }
    public String getCallcrd() {
        return callcrd;
    }
    public String getHaspdf() {
        return haspdf;
    }
    public String getTotalbooks() {
        return totalbooks;
    }
    public String getPubname() {
        return pubname;
    }
    public String getPubdate() {
        return pubdate;
    }
    public String getIsbn() {
        return isbn;
    }
    public String getIssn() {
        return issn;
    }
    public String getLbs() {
        return lbs;
    }
}