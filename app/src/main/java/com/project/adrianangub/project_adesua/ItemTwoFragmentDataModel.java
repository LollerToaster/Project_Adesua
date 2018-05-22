package com.project.adrianangub.project_adesua;

/**
 * Created by adrian angub on 19/05/2018.
 */

public class ItemTwoFragmentDataModel {

    //virtual classroom

    private String vcStudId;
    private String vcStudIdFk;
    private String vcStudStatus;
    private String vclassIdFk;
    private String vclassId;
    private String vclassPubId;
    private String vclassName;
    private String vclassGrade;
    private String vclassDesc;
    private String vclassNumStud;
    private String vclassNumBook;
    private String vclassTeachFk;
    private String schoolIdFk;

    //teachers

    private String teacherFulname;
    private String teacherUsername;
    private String teacherEmail;
    private String teacherIni;

    public ItemTwoFragmentDataModel(String vcStudId, String vcStudIdFk, String vcStudStatus, String vclassIdFk, String vclassId, String vclassPubId, String vclassName, String vclassGrade, String vclassDesc, String vclassNumStud, String vclassNumBook, String vclassTeachFk, String schoolIdFk, String teacherFulname, String teacherUsername, String teacherEmail, String teacherIni) {
        this.vcStudId = vcStudId;
        this.vcStudIdFk = vcStudIdFk;
        this.vcStudStatus = vcStudStatus;
        this.vclassIdFk = vclassIdFk;
        this.vclassId = vclassId;
        this.vclassPubId = vclassPubId;
        this.vclassName = vclassName;
        this.vclassGrade = vclassGrade;
        this.vclassDesc = vclassDesc;
        this.vclassNumStud = vclassNumStud;
        this.vclassNumBook = vclassNumBook;
        this.vclassTeachFk = vclassTeachFk;
        this.schoolIdFk = schoolIdFk;

        this.teacherFulname = teacherFulname;
        this.teacherUsername = teacherUsername;
        this.teacherEmail = teacherEmail;
        this.teacherIni = teacherIni;
    }

    public String getVcStudId() {
        return vcStudId;
    }
    public String getVcStudIdFk() {
        return vcStudIdFk;
    }
    public String getVcStudStatus() {
        return vcStudStatus;
    }
    public String getVclassIdFk() {
        return vclassIdFk;
    }
    public String getVclassId() {
        return vclassId;
    }
    public String getVclassPubId() {
        return vclassPubId;
    }
    public String getVclassName() {
        return vclassName;
    }
    public String getVclassGrade() {
        return vclassGrade;
    }
    public String getVclassDesc() {
        return vclassDesc;
    }
    public String getVclassNumStud() {
        return vclassNumStud;
    }
    public String getVclassNumBook() {
        return vclassNumBook;
    }
    public String getVclassTeachFk() {
        return vclassTeachFk;
    }
    public String getSchoolIdFk() {
        return schoolIdFk;
    }

    public String getTeacherFulname() {
        return teacherFulname;
    }
    public String getTeacherUsername() {
        return teacherUsername;
    }
    public String getTeacherEmail() {
        return teacherEmail;
    }
    public String getTeacherIni() {
        return teacherIni;
    }
}