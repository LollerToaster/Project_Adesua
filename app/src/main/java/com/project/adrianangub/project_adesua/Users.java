package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 10/05/2018.
 */

public class Users {

    //private String username;
    //private String password;
    private String uid;
    private String desc;
    private String meta;
    private String stat;

    public Users(String uid, String desc, String meta, String stat) {
        //this.username = username;
        //this.password = password;
        this.uid = uid;
        this.desc = desc;
        this.meta = meta;
        this.stat = stat;
    }

    /*
    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    */

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
}