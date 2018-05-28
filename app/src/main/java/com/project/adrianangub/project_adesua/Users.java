package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 10/05/2018.
 */

public class Users {

    private String uid;
    private String desc;
    private String meta;
    private String stat;
    private String schoolid;
    private String schoolname;
    private String schooladdress;
    private String schoolacryn;
    private String schoolnum;
    private String fullname;

    public Users(String uid, String desc, String meta, String stat, String schoolid, String schoolname, String schooladdress, String schoolacryn, String schoolnum, String fullname) {
        this.uid = uid;
        this.desc = desc;
        this.meta = meta;
        this.stat = stat;
        this.schoolid = schoolid;
        this.schoolname = schoolname;
        this.schooladdress = schooladdress;
        this.schoolacryn = schoolacryn;
        this.schoolnum = schoolnum;
        this.fullname = fullname;
    }

    public String getUid() {
        return uid;
    }
    public String getDesc() {
        return desc;
    }
    public String getMeta() {
        return meta;
    }
    public String getStat() {
        return stat;
    }
    public String getSchoolid() {
        return schoolid;
    }
    public String getSchoolname() {
        return schoolname;
    }
    public String getSchooladdress() {
        return schooladdress;
    }
    public String getSchoolacryn() {
        return schoolacryn;
    }
    public String getSchoolnum() {
        return schoolnum;
    }
    public String getFullname() {
        return fullname;
    }
}